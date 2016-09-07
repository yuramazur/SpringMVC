package ua.controller;

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

import ua.entity.Producer;
import ua.entity.Product;
import ua.entity.ProductType;
import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;
import ua.service.implementation.editor.ProducerEditor;
import ua.service.implementation.editor.ProductTypeEditor;
import ua.service.implementation.validator.ProductValidator;

@Controller
public class ProductController {
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProducerService producerService;
	@Autowired
	private ProductService productService;

	@InitBinder
	protected void initBinderProduct(WebDataBinder binderProduct) {
		binderProduct.registerCustomEditor(ProductType.class,
				new ProductTypeEditor(productTypeService));
		binderProduct.registerCustomEditor(Producer.class, new ProducerEditor(
				producerService));
		binderProduct.setValidator(new ProductValidator(productService));
	}

	@ModelAttribute("product")
	public Product getProduct() {
		return new Product();
	}

	@RequestMapping("/admin/product")
	public String showProduct(Model model) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "adminProduct";
	}

	// @RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	// public String saveProduct(@RequestParam String name,
	// @RequestParam String price, @RequestParam int productTypeId,
	// @RequestParam int producerId) {
	// double priceDouble = 0;
	// try {
	// priceDouble = Double.parseDouble(price);
	// } catch (NumberFormatException e) {
	// priceDouble = 150000;
	// }
	// productService.save(name, priceDouble, productTypeId, producerId);
	// return "redirect:/admin/product";
	// }

	@RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	public String saveProduct(
			@ModelAttribute("product") @Valid Product product,
			BindingResult br, Model model) {
		if(br.hasErrors()){
			model.addAttribute("products", productService.findAll());
			model.addAttribute("productTypes", productTypeService.findAll());
			model.addAttribute("producers", producerService.findAll());
			return "adminProduct";
		}
		productService.save(product);
		return "redirect:/admin/product";
	}

	@RequestMapping(value = "/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteById(id);
		return "redirect:/admin/product";
	}

	@RequestMapping(value = "/admin/product/update/{id}")
	public String updateProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", productService.findById(id));
		model.addAttribute("products", productService.findAll());
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "adminProduct";
	}

	@RequestMapping("/user/product/{id}")
	public String showUserProduct(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "userProduct";
	}
}
