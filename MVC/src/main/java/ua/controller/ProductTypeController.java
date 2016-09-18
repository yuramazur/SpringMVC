package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import ua.form.NameFilterForm;

import ua.form.ProductTypeFilterForm;
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

	@ModelAttribute("filter")
	public ProductTypeFilterForm getFilter() {
		return new ProductTypeFilterForm();
	}

	@InitBinder("productType")
	protected void initBiderProductType(WebDataBinder binderProductType) {
		binderProductType.setValidator(new ProductTypeValidator(
				productTypeService));
	}

	@RequestMapping("/admin/producttype")
	public String showProductType(Model model,@PageableDefault(size = 5, sort = "name") Pageable pageable,
			@ModelAttribute("filter") ProductTypeFilterForm form) {
		model.addAttribute("page", productTypeService.findAllPageableFilter(pageable,form));
		return "adminProductType";
	}
	
	@RequestMapping(value = "/admin/producttype", method = RequestMethod.POST)
	public String saveProductType(
			@ModelAttribute("productType") @Valid ProductType productType,
			BindingResult br, Model model,@PageableDefault(size = 5) Pageable pageable,
			@ModelAttribute("filter") ProductTypeFilterForm form) {
		if (br.hasErrors()) {
			model.addAttribute("page", productTypeService.findAllPageableFilter(pageable,form));
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
	private String updateProductType(@PathVariable int id, Model model,@PageableDefault(size = 5) Pageable pageable,
			@ModelAttribute("filter") ProductTypeFilterForm form) {
		model.addAttribute("productType", productTypeService.findById(id));
		model.addAttribute("page", productTypeService.findAllPageableFilter(pageable,form));
		return "adminProductType";
	}
	@SuppressWarnings("unused")
	private String getParams(Pageable pageable, NameFilterForm form) {
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
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
