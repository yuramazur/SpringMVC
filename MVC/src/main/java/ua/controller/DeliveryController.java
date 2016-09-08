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
import ua.entity.City;
import ua.entity.Delivery;
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

	@InitBinder
	protected void initBinderDelivery(WebDataBinder binderDelivery) {
		binderDelivery.registerCustomEditor(City.class, new CityEditor(
				cityService));
		binderDelivery.registerCustomEditor(Carrier.class, new CarrierEditor(
				carrierService));
		binderDelivery.setValidator(new DeliveryValidator(deliveryService));
	}

	@ModelAttribute("delivery")
	public Delivery getDelivery() {
		return new Delivery();
	}

	@RequestMapping("/admin/delivery")
	public String showDelyvery(Model model) {
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("size", deliveryService.findAll().size());
		return "adminDelivery";
	}

	// @RequestMapping(value = "/admin/delivery", method = RequestMethod.POST)
	// public String saveDelivery(@RequestParam int cityId,
	// @RequestParam int carrierId, @RequestParam String numCerrDep) {
	// int intNumCerrDep = 0;
	// try {
	// intNumCerrDep = Integer.parseInt(numCerrDep);
	// } catch (Exception e) {
	// intNumCerrDep = 0;
	// }
	// deliveryService.save(cityId, carrierId, intNumCerrDep);
	// return "redirect:/admin/delivery";
	// }
	@RequestMapping(value = "/admin/delivery", method = RequestMethod.POST)
	public String saveDelivery(
			@ModelAttribute("delivery") @Valid Delivery delivery,
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("cities", cityService.findAll());
			model.addAttribute("carriers", carrierService.findAll());
			model.addAttribute("deliveries", deliveryService.findAll());
			return "adminDelivery";
		}
		deliveryService.save(delivery);
		return "redirect:/admin/delivery";
	}

	@RequestMapping("/admin/delivery/delete/{id}")
	public String deleteDelivery(@PathVariable int id) {
		deliveryService.deleteById(id);
		return "redirect:/admin/delivery";
	}

	@RequestMapping(value = "/admin/delivery/update/{id}")
	public String updadeDelivery(Model model, @PathVariable int id) {
		model.addAttribute("delivery", deliveryService.findById(id));
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("size", deliveryService.findAll().size());
		return "adminDelivery";
	}
}
