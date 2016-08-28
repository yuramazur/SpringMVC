package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyOrderController {
	@RequestMapping("/admin/order")
	public String showMyOrder() {
		return "adminOrder";
	}
	@RequestMapping("/user/search/product/order")
	public String showUserOrder() {
		return "userOrder";
	}
}
