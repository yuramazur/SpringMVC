package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Product;
import ua.service.ProductService;

public class ProductValidator implements Validator {

	private final ProductService productService;
	private static final Pattern price = Pattern
			.compile("^[^0][0-9]*[^,|\\.](\\.[0-9]{2,2})?");

	public ProductValidator(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;

		if (product.getProducer() == null) {
			errors.rejectValue("producer", "", "Select producer!");
		}
		if (product.getProductType() == null) {
			errors.rejectValue("productType", "", "Select product type!");
		}
		if (errors.getFieldError("producer") == null
				&& errors.getFieldError("productType") == null
				&& productService.findProduct(product) != null) {
			errors.rejectValue("error", "", "Product already exists!");
		}

		// if (product.getProducer() == null) {
		// errors.rejectValue("producer", "", "Select producer!");
		// if (product.getProductType() == null) {
		// errors.rejectValue("productType", "", "Select product type!");
		// }
		// } else if (product.getProductType() == null) {
		// errors.rejectValue("productType", "", "Select product type!");
		// } else if (productService.findProduct(product) != null) {
		// errors.rejectValue("error", "", "Product already exists!");
		// }

		Matcher m = price.matcher(product.getPrice());

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "",
				"Can not be empty!");
		if(errors.getFieldError("price")==null & !m.matches()){
			errors.rejectValue("price", "", "Incorrect price format!");
		}
			
//		if (!m.matches() & !product.getPrice().equals("")) {
//			errors.rejectValue("price", "", "Incorrect price format!");
//		} else if (product.getPrice().equals("")) {
//			errors.rejectValue("price", "", "Can not be empty!");
//		}
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can not be empty!");

	}

}
