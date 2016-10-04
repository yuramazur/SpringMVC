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
import ua.entity.Producer;
import ua.entity.ProductType;
import ua.form.ProductForm;
import ua.form.filter.ProductFilterForm;
import ua.service.ProducerService;
import ua.service.ProductService;
import ua.service.ProductTypeService;
import ua.service.UserService;
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
	@Autowired
	private UserService userService;

	@InitBinder("productForm")
	protected void initBinderProduct(WebDataBinder binderProduct) {
		binderProduct.registerCustomEditor(ProductType.class,
				new ProductTypeEditor(productTypeService));
		binderProduct.registerCustomEditor(Producer.class, new ProducerEditor(
				producerService));
		binderProduct.setValidator(new ProductValidator(productService));
	}

	@ModelAttribute("productForm")
	public ProductForm getForm() {
		return new ProductForm();
	}

	@ModelAttribute("filter")
	public ProductFilterForm getFilter() {
		return new ProductFilterForm();
	}

	@RequestMapping("/admin/product")
	public String showProduct(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") ProductFilterForm filter) {
		model.addAttribute("page",
				productService.findAllPagebleFilter(pageable, filter));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());
		return "adminProduct";
	}

	@RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	public String saveProduct(
			@ModelAttribute("productForm") @Valid ProductForm productForm,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") ProductFilterForm filter) {
		if (br.hasErrors()) {
			model.addAttribute("page",
					productService.findAllPagebleFilter(pageable, filter));
			model.addAttribute("productTypes", productTypeService.findAll());
			model.addAttribute("producers", producerService.findAll());
			return "adminProduct";
		}
		productService.save(productForm);
		return "redirect:/admin/product" + getParams(pageable, filter);
	}

	@RequestMapping(value = "/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") ProductFilterForm filter) {
		productService.deleteById(id);
		return "redirect:/admin/product" + getParams(pageable, filter);
	}

	@RequestMapping(value = "/admin/product/update/{id}")
	public String updateProduct(Model model, @PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value = "filter") ProductFilterForm filter) {
		model.addAttribute("productForm", productService.findFormById(id));
		model.addAttribute("page",
				productService.findAllPagebleFilter(pageable, filter));
		model.addAttribute("productTypes", productTypeService.findAll());
		model.addAttribute("producers", producerService.findAll());

		return "adminProduct";
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
		buffer.append("&name=");
		buffer.append(form.getName());
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
