package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.form.AddOrderForm;

import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;
import ua.service.UserService;

@Controller
public class WishListAndOrderController {

	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProducerService producerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@ModelAttribute("addOrderForm")
	public AddOrderForm getForm() {
		return new AddOrderForm();
	}

	@RequestMapping("/user/wishlist")
	public String showProductWishList(Model model, Principal principal) {
		int id = Integer.parseInt(principal.getName());
		model.addAttribute("products", userService.getWishList(id));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "userWishList";
	}
	@RequestMapping("/user/order")
	public String showProductOrder(Model model, @ModelAttribute("addOrderForm") AddOrderForm form) {
		model.addAttribute("products", productService.findAllSelected(form.getProductIds()));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "userOrder";
	}
	
	
	@RequestMapping(value = "/user/wishlist/delete/{id}")
	public String deleteFromWishList(@PathVariable int id, Principal principal) {
		userService.deleteFromWishList(principal, id);
		return "redirect:/user/wishlist";
	}
}
