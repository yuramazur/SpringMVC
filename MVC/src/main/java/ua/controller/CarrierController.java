package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@InitBinder
	protected void InitBinderCarrier(WebDataBinder binderCarrier) {
		binderCarrier.setValidator(new CarrierValidator(carrierService));
	}

	@RequestMapping(value = "/admin/carrier", method = RequestMethod.POST)
	public String saveCarrier(
			@ModelAttribute("carrier") @Valid Carrier carrier,
			BindingResult br, Model model) {
		if(br.hasErrors()){
			model.addAttribute("carriers", carrierService.findAll());
			return "adminCarrier";
		}
		carrierService.save(carrier);
		return "redirect:/admin/carrier";
	}

	@RequestMapping("/admin/carrier")
	public String showName(Model model) {
		model.addAttribute("carriers", carrierService.findAll());
		return "adminCarrier";
	}

	// @RequestMapping(value = "/admin/carrier", method = RequestMethod.POST)
	// public String saveName(@RequestParam String name) {
	// carrierService.save(name);
	// return "redirect:/admin/carrier";
	// }

	@RequestMapping("/admin/carrier/delete/{id}")
	public String deleteCarrier(@PathVariable int id) {
		carrierService.deleteById(id);
		return "redirect:/admin/carrier";
	}

	@RequestMapping("/admin/carrier/update/{id}")
	public String updateCarrier(@PathVariable int id, Model model) {
		model.addAttribute("carrier", carrierService.findById(id));
		model.addAttribute("carriers", carrierService.findAll());
		return "adminCarrier";
	}
}
