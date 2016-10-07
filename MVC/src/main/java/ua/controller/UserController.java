package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.UserForm;
import ua.service.UserService;
import ua.service.implementation.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@InitBinder("userForm")
	protected void initBinderProduct(WebDataBinder binder) {

		binder.setValidator(new UserValidator(userService));
	}

	@ModelAttribute("userForm")
	public UserForm getForm() {
		return new UserForm();
	}

	@RequestMapping("/registration")
	public String register() {
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String save(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult br) {
		if (br.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);
		return "redirect:/login";
	}

}