package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String showIndex() {
		return "index";

	}
	
	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
	}
	
	@RequestMapping("/user")
	public String showUser(){
		return "user";
	}
}
