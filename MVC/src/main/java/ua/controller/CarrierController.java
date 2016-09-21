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
import ua.form.filter.CarrierFilterForm;
import ua.form.filter.NameFilterForm;
import ua.service.CarrierService;
import ua.service.implementation.validator.CarrierValidator;

@Controller
public class CarrierController {
	@Autowired
	private CarrierService carrierService;

	@ModelAttribute("carrier")
	public Carrier getCarrier() {
		return new Carrier();
	}

	@ModelAttribute("filter")
	public CarrierFilterForm getFilter() {
		return new CarrierFilterForm();
	}

	@InitBinder("carrier")
	protected void InitBinder(WebDataBinder binder) {
		binder.setValidator(new CarrierValidator(carrierService));
	}

	@RequestMapping("/admin/carrier")
	public String showName(Model model,
			@PageableDefault(size = 5, sort = "name") Pageable pageable, @ModelAttribute("filter") CarrierFilterForm form) {
		model.addAttribute("page", carrierService.findAllPageableForm(pageable,form));
		return "adminCarrier";
	}

	@RequestMapping(value = "/admin/carrier", method = RequestMethod.POST)
	public String saveCarrier(
			@ModelAttribute("carrier") @Valid Carrier carrier,
			BindingResult br, Model model,
			@PageableDefault(size = 5) Pageable pageable, @ModelAttribute("filter") CarrierFilterForm form) {
		if (br.hasErrors()) {
			model.addAttribute("page", carrierService.findAllPageableForm(pageable,form));
			return "adminCarrier";
		}
		carrierService.save(carrier);
		return "redirect:/admin/carrier";
	}

	@RequestMapping("/admin/carrier/delete/{id}")
	public String deleteCarrier(@PathVariable int id) {
		carrierService.deleteById(id);
		return "redirect:/admin/carrier";
	}

	@RequestMapping("/admin/carrier/update/{id}")
	public String updateCarrier(@PathVariable int id, Model model,
			@PageableDefault(size = 5) Pageable pageable, @ModelAttribute("filter") CarrierFilterForm form) {
		model.addAttribute("carrier", carrierService.findById(id));
		model.addAttribute("page", carrierService.findAllPageableForm(pageable,form));
		return "adminCarrier";
	}
	@SuppressWarnings("unused")
	private String getParams(Pageable pageable, NameFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
