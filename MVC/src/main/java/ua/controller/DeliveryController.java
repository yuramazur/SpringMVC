package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.CarrierService;
import ua.service.CityService;
import ua.service.DeliveryService;

@Controller
public class DeliveryController {
	@Autowired
	public CityService cityService;
	@Autowired
	public CarrierService carrierService;
	@Autowired
	public DeliveryService deliveryService;

	@RequestMapping("/admin/delivery")
	public String showDelyvery(Model model) {
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("size", deliveryService.findAll().size());
		return "adminDelivery";
	}

	@RequestMapping(value = "/admin/delivery", method = RequestMethod.POST)
	public String saveDelivery(@RequestParam int cityId,
			@RequestParam int carrierId, @RequestParam String numCerrDep) {
		int intNumCerrDep = 0;
		try {
			intNumCerrDep = Integer.parseInt(numCerrDep);
		} catch (Exception e) {
			intNumCerrDep = 0;
		}
		deliveryService.save(cityId, carrierId,intNumCerrDep);
		return "redirect:/admin/delivery";
	}

	@RequestMapping("/admin/delivery/delete/{id}")
	public String deletedelivery(@PathVariable int id) {
		deliveryService.deleteById(id);
		return "redirect:/admin/delivery";
	}
}
