package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.CarrierService;
import ua.service.CityService;

@Controller
public class DeliveryController {
	@Autowired
	public CityService cityService;
	@Autowired
	public CarrierService carrierService;
	
//	public DeliveryService deliveryService;
//	
	@RequestMapping("admin/delivery")
	public String showDelyvery(Model model) {
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		return "adminDelivery";
	}
}
