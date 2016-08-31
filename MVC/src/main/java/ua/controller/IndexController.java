package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.ProductService;

@Controller
public class IndexController {
	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String showIndex() {
		return "index";

	}

	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
	}

	@RequestMapping("/user")
	public String showUser(Model model) {
		model.addAttribute("products", productService.findAll());
		return "user";
	}

//	@RequestMapping("/user/product/{id}")
//	public String showUserProduct(@PathVariable int id, Model model) {
//		model.addAttribute("product", productService.findById(id));
//		return "userProduct";
//	}
}
