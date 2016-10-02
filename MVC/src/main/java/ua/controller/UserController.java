package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




import ua.form.UserForm;
import ua.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@ModelAttribute("userForm")
	public UserForm getForm(){
		return new UserForm();
	}
	@RequestMapping("/registration")
	public String register(){
		return "registration";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String save(@ModelAttribute("userForm") UserForm userForm){
		userService.save(userForm);
		return "redirect:/login";
	}
	
	@RequestMapping("/user/wishlist/add/{uId}/{pId}")
	public String deleteName(@PathVariable int uId,int pId) {
		userService.addToWishList(uId,pId);
		return "redirect:/user";
	}
}