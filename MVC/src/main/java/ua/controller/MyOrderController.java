package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.form.AddOrderForm;
import ua.service.ProductService;

@Controller
public class MyOrderController {
	@Autowired
	private ProductService productService;

	@ModelAttribute("addOrderForm")
	public AddOrderForm getForm() {
		return new AddOrderForm();
	}

	@RequestMapping("/admin/order")
	public String showMyOrder() {
		return "adminOrder";
	}

	@RequestMapping("/user/search/product/order")
	public String showUserOrder() {
		return "userOrder";
	}
}
