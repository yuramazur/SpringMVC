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

import ua.entity.City;
import ua.service.CityService;
import ua.service.implementation.validator.CityValidator;

@Controller
public class CityController {
	@Autowired
	private CityService cityService;

	@ModelAttribute("city")
	public City getCity() {
		return new City();
	}

	@InitBinder
	protected void initBinderCity(WebDataBinder binderCity) {
		binderCity.setValidator(new CityValidator(cityService));
	}

	@RequestMapping("/admin/city")
	public String showCity(Model model) {
		model.addAttribute("cities", cityService.findAll());
		return "adminCity";
	}

	@RequestMapping("/admin/city/delete/{id}")
	public String deleteCity(@PathVariable int id) {
		cityService.deleteById(id);
		;
		return "redirect:/admin/city";
	}

	// @RequestMapping(value = "/admin/city", method = RequestMethod.POST)
	// public String saveCity(@RequestParam String name) {
	// cityService.save(name);
	// return "redirect:/admin/city";
	// }

	@RequestMapping(value = "/admin/city", method = RequestMethod.POST)
	public String saveCity(@ModelAttribute("city") @Valid City city,
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("cities", cityService.findAll());
			return "adminCity";

		}
		cityService.save(city);
		return "redirect:/admin/city";
	}

	@RequestMapping("/admin/city/update/{id}")
	public String updateCity(@PathVariable int id, Model model) {
		model.addAttribute("city", cityService.findById(id));
		model.addAttribute("cities", cityService.findAll());
		return "adminCity";
	}
}
