package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Carrier;
import ua.entity.City;

import ua.form.DeliveryForm;

import ua.form.filter.DeliveryFilterForm;
import ua.service.CarrierService;
import ua.service.CityService;
import ua.service.DeliveryService;
import ua.service.implementation.editor.CarrierEditor;
import ua.service.implementation.editor.CityEditor;
import ua.service.implementation.validator.DeliveryValidator;

@Controller
public class DeliveryController {
	@Autowired
	public CityService cityService;
	@Autowired
	public CarrierService carrierService;
	@Autowired
	public DeliveryService deliveryService;

	@InitBinder("deliveryForm")
	protected void initBinderDelivery(WebDataBinder binderDelivery) {
		binderDelivery.registerCustomEditor(City.class, new CityEditor(
				cityService));
		binderDelivery.registerCustomEditor(Carrier.class, new CarrierEditor(
				carrierService));
		binderDelivery.setValidator(new DeliveryValidator(deliveryService));
	}

	@ModelAttribute("deliveryForm")
	public DeliveryForm getDeliveryForm() {
		return new DeliveryForm();
	}

	@ModelAttribute("filter")
	public DeliveryFilterForm getFilter() {
		return new DeliveryFilterForm();
	}

	@RequestMapping("/admin/delivery")
	public String showDelyvery(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") DeliveryFilterForm filter) {
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		model.addAttribute("page",
				deliveryService.findAllPagebleFilter(pageable, filter));

		return "adminDelivery";
	}

	@RequestMapping(value = "/admin/delivery", method = RequestMethod.POST)
	public String saveDelivery(
			@ModelAttribute("deliveryForm") @Valid DeliveryForm deliveryForm,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") DeliveryFilterForm filter) {
		if (br.hasErrors()) {
			model.addAttribute("cities", cityService.findAll());
			model.addAttribute("carriers", carrierService.findAll());
			model.addAttribute("page",
					deliveryService.findAllPagebleFilter(pageable, filter));
			return "adminDelivery";
		}
		deliveryService.save(deliveryForm);
		return "redirect:/admin/delivery" + getParams(pageable, filter);
	}

	@RequestMapping("/admin/delivery/delete/{id}")
	public String deleteDelivery(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") DeliveryFilterForm filter) {
		deliveryService.deleteById(id);
		return "redirect:/admin/delivery" + getParams(pageable, filter);
	}

	@RequestMapping(value = "/admin/delivery/update/{id}")
	public String updadeDelivery(@PathVariable int id, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") DeliveryFilterForm filter) {
		model.addAttribute("deliveryForm", deliveryService.findFormById(id));
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		model.addAttribute("page",
				deliveryService.findAllPagebleFilter(pageable, filter));

		return "adminDelivery";
	}

	private String getParams(Pageable pageable, DeliveryFilterForm form) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		buffer.append("&numCerrDep=");
		buffer.append(form.getNumCerrDepInt());

		for (Integer i : form.getCityIds()) {
			buffer.append("&cityIds=");
			buffer.append(i.toString());
		}
		for (Integer i : form.getCarrierIds()) {
			buffer.append("&carrierIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
	}
}
