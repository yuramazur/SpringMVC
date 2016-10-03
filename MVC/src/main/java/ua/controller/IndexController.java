package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.MailSenderForm;
import ua.form.filter.ProductFilterForm;
import ua.service.MailSender;
import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;
import ua.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProducerService producerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailSender mailSender;

	@ModelAttribute("filter")
	public ProductFilterForm getFilter() {
		return new ProductFilterForm();
	}

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
	public String showProduct(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") ProductFilterForm filter) {
		model.addAttribute("page",
				productService.findAllPagebleFilter(pageable, filter));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "user";
	}

	@RequestMapping("/user/wishlist/add/{id}")
	public String deleteName(@PathVariable int id, Principal principal,@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") ProductFilterForm filter) {
		int uId = Integer.valueOf(principal.getName());
		userService.addToWishList(uId, id);
		return "redirect:/user"+ getParams(pageable, filter);
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	
	private String getParams(Pageable pageable, ProductFilterForm form) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		buffer.append("&minPrice=");
		buffer.append(form.getMinPrice());
		buffer.append("&maxPrice=");
		buffer.append(form.getMaxPrice());
		buffer.append("&nameSearch=");
		buffer.append(form.getNameSearch());
		for (Integer i : form.getProductTypeIds()) {
			buffer.append("&productTypeIds=");
			buffer.append(i.toString());
		}
		for (Integer i : form.getProducerIds()) {
			buffer.append("&ProducerIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
	}
}
