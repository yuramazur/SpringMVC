package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.CarrierService;


@Controller
public class CarrierController {
	@Autowired
	private CarrierService carrierService;

	@RequestMapping("/admin/carrier")
	public String showName(Model model) {
		model.addAttribute("carrier", carrierService.findAll());
		return "adminCarrier";
	}
	
	@RequestMapping(value = "/admin/carrier", method = RequestMethod.POST)
	public String saveName(@RequestParam String name){
		carrierService.save(name);		
		return "redirect:/admin/carrier";
	}
	@RequestMapping("/admin/carrier/delete/{id}")
	public String deleteName(@PathVariable int id){
		carrierService.deleteById(id);
		return "redirect:/admin/carrier";
	}
}
