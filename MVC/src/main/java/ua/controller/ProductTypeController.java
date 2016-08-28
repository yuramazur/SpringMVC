package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.ProductTypeService;

@Controller
public class ProductTypeController {
	@Autowired
	public ProductTypeService productTypeService;
	
	@RequestMapping("/admin/producttype")
	public String showProductType(Model model) {
		model.addAttribute("productType", productTypeService.findAll());
		return "adminProductType";
	}
	@RequestMapping(value ="/admin/producttype", method = RequestMethod.POST)
	public String saveProductType(@RequestParam String name) {
		productTypeService.save(name);
		return "redirect:/admin/producttype";
	}
	@RequestMapping("/admin/producttype/delete/{id}")
	public String deleteProductType(@PathVariable int id) {
		productTypeService.deleteById(id);
		return"redirect:/admin/producttype";
	}
}
