package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.MailSenderForm;
import ua.service.MailSender;
import ua.service.ProductService;

@Controller
public class IndexController {
	@Autowired
	private ProductService productService;
	@Autowired
	private MailSender mailSender;

	@RequestMapping("/")
	public String showIndex() {
		return "index";

	}

	@ModelAttribute("mailSender")
	public MailSenderForm getMailSender() {
		return new MailSenderForm();
	}

	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("mailSender") MailSenderForm form) {
		mailSender.sendMail(form.getContent(), form.getEmail(),
				form.getMailBody());
		return "redirect:/admin";
	}

	@RequestMapping("/user")
	public String showUser(Model model) {
		model.addAttribute("products", productService.findAll());
		return "user";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
}
