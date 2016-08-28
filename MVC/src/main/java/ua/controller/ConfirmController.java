package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfirmController {

	@RequestMapping("/user/search/product/order/confirmation")
	public String showConfirm() {
		return "userConfirm";
	}
}
