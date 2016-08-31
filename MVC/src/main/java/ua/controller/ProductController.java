package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;

@Controller
public class ProductController {
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProducerService producerService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/admin/product")
	public String showProduct(Model model) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "adminProduct";
	}

	@RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	public String saveProduct(@RequestParam String name,
			@RequestParam String price, @RequestParam int productTypeId,
			@RequestParam int producerId) {
		double priceDouble = 0;
		try {
			priceDouble = Double.parseDouble(price);
		} catch (NumberFormatException e) {
			priceDouble = 150000;
		}
		productService.save(name, priceDouble, productTypeId, producerId);
		return "redirect:/admin/product";
	}

	@RequestMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteById(id);
		return "redirect:/admin/product";
	}

	@RequestMapping("/user/product/{id}")
	public String showUserProduct(@PathVariable int id,Model model) {
		model.addAttribute("product", productService.findById(id));
		return "userProduct";
	}
}
