package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.CityService;

@Controller
public class CityController {
	@Autowired
	private CityService cityService;

	@RequestMapping("/admin/city")
	public String showCity(Model model) {
		model.addAttribute("city", cityService.findAll());
		return "adminCity";
	}

	@RequestMapping("/admin/city/delete/{id}")
	public String deleteCity(@PathVariable int id) {
		cityService.deleteById(id);;
		return "redirect:/admin/city";
	}

	@RequestMapping(value = "/admin/city", method = RequestMethod.POST)
	public String saveCity(@RequestParam String name) {
		cityService.save(name);
		return "redirect:/admin/city";
	}
}
