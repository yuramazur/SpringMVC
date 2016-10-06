package ua.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





import ua.entity.Carrier;
import ua.entity.City;
import ua.form.AddOrderForm;
import ua.form.DeliveryForm;
import ua.service.CarrierService;
import ua.service.CityService;
import ua.service.DeliveryService;
import ua.service.OrderService;
import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;
import ua.service.UserService;
import ua.service.implementation.editor.CarrierEditor;
import ua.service.implementation.editor.CityEditor;
import ua.service.implementation.validator.DeliveryValidator;

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
	@Autowired
	private DeliveryService deliveryService;

	@ModelAttribute("addOrderForm")
	public AddOrderForm getOrderForm() {
		return new AddOrderForm();
	}
	@InitBinder("deliveryForm")
	protected void initBinderDelivery(WebDataBinder binderDelivery) {
		binderDelivery.registerCustomEditor(City.class, new CityEditor(
				cityService));
		binderDelivery.registerCustomEditor(Carrier.class, new CarrierEditor(
				carrierService));
		binderDelivery.setValidator(new DeliveryValidator(deliveryService));
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
			@ModelAttribute("addOrderForm") AddOrderForm addForm,
			Principal principal) {
		model.addAttribute("products",
				productService.findAllInited(addForm.getProductIds()));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("cities", cityService.findAll());
		model.addAttribute("carriers", carrierService.findAll());
		return "userOrder";
	}

	@RequestMapping(value = "/user/order/save", method = RequestMethod.POST)
	public String saveOrder(Model model,
			@ModelAttribute("addOrderForm") AddOrderForm addForm,
			@ModelAttribute("deliveryForm") @Valid DeliveryForm deliveryForm,
			BindingResult br, Principal principal) {
		if (br.hasErrors()) {
			model.addAttribute("products",
					productService.findAllInited(addForm.getProductIds()));
			model.addAttribute("productTypes", productTypeService.findAll());
			model.addAttribute("producers", producerService.findAll());
			model.addAttribute("cities", cityService.findAll());
			model.addAttribute("carriers", carrierService.findAll());
			return "userOrder";
		}

		orderService.saveOrder(deliveryForm, addForm, principal);
		for (Integer id : addForm.getProductIds()) {
			userService.deleteFromWishList(principal, id);
		}
		return "redirect:/user";
	}

	@RequestMapping(value = "/user/wishlist/delete/{id}")
	public String deleteFromWishList(@PathVariable int id, Principal principal) {
		userService.deleteFromWishList(principal, id);
		return "redirect:/user/wishlist";
	}
}
