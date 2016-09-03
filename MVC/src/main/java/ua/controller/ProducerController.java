package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Producer;
import ua.service.ProducerService;

@Controller
public class ProducerController {
	@Autowired
	private ProducerService producerService;

	@RequestMapping("/admin/producer")
	public String showProducer(Model model) {
		model.addAttribute("producers", producerService.findAll());
		return "adminProducer";
	}

	// @RequestMapping(value = "/admin/producer", method = RequestMethod.POST)
	// public String saveProducer(@RequestParam String name) {
	// producerService.save(name);
	// return "redirect:/admin/producer";
	// }

	@RequestMapping("/admin/producer/delete/{id}")
	public String deleteProducer(@PathVariable int id) {
		producerService.deleteById(id);
		return "redirect:/admin/producer";
	}

	@RequestMapping("/admin/producer/update/{id}")
	public String updateProducer(@PathVariable int id, Model model) {
		model.addAttribute("producer", producerService.findById(id));
		model.addAttribute("producers", producerService.findAll());
		return "adminProducer";
	}

	@ModelAttribute("producer")
	public Producer getProducer() {
		return new Producer();
	}

	@RequestMapping(value = "/admin/producer", method = RequestMethod.POST)
	public String saveProducer(
			@ModelAttribute("producer") @Valid Producer producer) {
		producerService.save(producer);
		return "redirect:/admin/producer";
	}
}
