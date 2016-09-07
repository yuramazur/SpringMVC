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

import ua.entity.ProductType;
import ua.service.ProductTypeService;
import ua.service.implementation.validator.ProductTypeValidator;

@Controller
public class ProductTypeController {
	@Autowired
	public ProductTypeService productTypeService;

	@ModelAttribute("productType")
	public ProductType getProductType() {
		return new ProductType();
	}

	@InitBinder
	protected void initBiderProductType(WebDataBinder binderProductType) {
		binderProductType.setValidator(new ProductTypeValidator(
				productTypeService));
	}

	@RequestMapping("/admin/producttype")
	public String showProductType(Model model) {
		model.addAttribute("productTypes", productTypeService.findAll());
		return "adminProductType";
	}

	// @RequestMapping(value = "/admin/producttype", method =
	// RequestMethod.POST)
	// public String saveProductType(@RequestParam String name) {
	// productTypeService.save(name);
	// return "redirect:/admin/producttype";
	// }

	@RequestMapping(value = "/admin/producttype", method = RequestMethod.POST)
	public String saveProductType(
			@ModelAttribute("productType") @Valid ProductType productType,
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("productTypes", productTypeService.findAll());
			return "adminProductType";
		}
		productTypeService.save(productType);
		return "redirect:/admin/producttype";
	}

	@RequestMapping("/admin/producttype/delete/{id}")
	public String deleteProductType(@PathVariable int id) {
		productTypeService.deleteById(id);
		return "redirect:/admin/producttype";
	}

	@RequestMapping("/admin/producttype/update/{id}")
	private String updateProductType(@PathVariable int id, Model model) {
		model.addAttribute("productType", productTypeService.findById(id));
		model.addAttribute("productTypes", productTypeService.findAll());
		return "adminProductType";
	}
}
