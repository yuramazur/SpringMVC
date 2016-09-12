package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	@InitBinder("carrier")
	protected void InitBinder(WebDataBinder binder) {
		binder.setValidator(new CarrierValidator(carrierService));
	}

	@RequestMapping("/admin/carrier")
	public String showName(Model model,
			@PageableDefault(size = 5, sort = "name") Pageable pageable) {
		model.addAttribute("carriers", carrierService.findAllPageable(pageable));
		return "adminCarrier";
	}

	@RequestMapping(value = "/admin/carrier", method = RequestMethod.POST)
	public String saveCarrier(
			@ModelAttribute("carrier") @Valid Carrier carrier,
			BindingResult br, Model model,
			@PageableDefault(size = 5) Pageable pageable) {
		if (br.hasErrors()) {
			model.addAttribute("carriers",
					carrierService.findAllPageable(pageable));
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
			@PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("carrier", carrierService.findById(id));
		model.addAttribute("carriers", carrierService.findAllPageable(pageable));
		return "adminCarrier";
	}
}
