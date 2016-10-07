package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.MailSenderForm;
import ua.form.filter.UserFilterForm;
import ua.service.ClientService;
import ua.service.MailSender;
import ua.service.NameService;
import ua.service.UserService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clienService;
	@Autowired
	private NameService nameService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailSender mailSender;

	@ModelAttribute("filter")
	public UserFilterForm getForm() {
		return new UserFilterForm()  ;
	}
	@ModelAttribute("mailSender")
	public MailSenderForm getMailSender() {
		return new MailSenderForm();
	}
	@RequestMapping("/admin/client")
	public String showClient(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") UserFilterForm filter) {
		model.addAttribute("page", userService.findAllPageable(pageable,filter));
		model.addAttribute("size", clienService.findAll().size());
		return "adminClient";
	}
	@RequestMapping(value = "/admin/client/send/{id}", method = RequestMethod.POST)
	public String sendMail(@PathVariable int id, @ModelAttribute("mailSender") MailSenderForm form) {
		form.setEmail(userService.findById(id).getMail());
		mailSender.sendMail(form.getContent(), form.getEmail(),
				form.getMailBody());
		return "redirect:/admin/client";
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
