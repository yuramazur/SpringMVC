package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.MyOrder;
import ua.form.AddOrderForm;
import ua.form.DeliveryForm;
import ua.service.CarrierService;
import ua.service.CityService;
import ua.service.OrderService;
import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;
import ua.service.UserService;

@Controller
public class WishListAndOrderController {
	@Autowired
	private CityService cityService;
	@Autowired
	private CarrierService carrierService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProducerService producerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	@ModelAttribute("addOrderForm")
	public AddOrderForm getOrderForm() {
		return new AddOrderForm();
	}

	@ModelAttribute("deliveryForm")
	public DeliveryForm getDeliveryForm() {
		return new DeliveryForm();
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
	public String showProductOrder(Model model,
			@ModelAttribute("addOrderForm") AddOrderForm addForm, @ModelAttribute("deliveryForm") DeliveryForm deliveryForm, Principal principal) {
		MyOrder order = orderService.createOrder(principal,addForm.getProductIds());
		
			
		
		model.addAttribute("products",orderService.findAllProducts(order.getId()));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		for (Integer id : addForm.getProductIds()) {
			userService.deleteFromWishList(principal, id);
		}
		
		return "userOrder";
	}

	@RequestMapping(value = "/user/wishlist/delete/{id}")
	public String deleteFromWishList(@PathVariable int id, Principal principal) {
		userService.deleteFromWishList(principal, id);
		return "redirect:/user/wishlist";
	}
}
