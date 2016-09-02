package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import ua.service.ClientService;
import ua.service.NameService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clienService;
	@Autowired
	private NameService nameService;

	@RequestMapping("/admin/client")
	public String showClient(Model model) {
		model.addAttribute("clients", clienService.findAll());
		model.addAttribute("size", clienService.findAll().size());
		return "adminClient";
	}

	@RequestMapping("/admin/client/delete/{id}")
	public String deleteClient(@PathVariable int id) {
		clienService.deleteById(id);
		return "redirect:/admin/client";
	}

	
	@RequestMapping(value = "/user/client", method = RequestMethod.POST)
	public String saveClient() {
		return "redirect:/user/client";
	}
}
