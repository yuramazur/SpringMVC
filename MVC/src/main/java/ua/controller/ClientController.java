package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {
@RequestMapping("/admin/client")
	public String showClient() {
		return "adminClient";
	}
}
