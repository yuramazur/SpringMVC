package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.ProductForm;
import ua.service.ProductService;

public class ProductValidator implements Validator {

	private final ProductService productService;
	private static final Pattern price = Pattern
			.compile("^[1-9]{1,1}([0-9]{0,10}[^,|\\.])?(\\.[0-9]{2,2})?$");

	public ProductValidator(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductForm productForm = (ProductForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "",
				"Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "",
				"Can not be empty!");
		if (productForm.getPrice() == null) {
			errors.rejectValue("price", "", "Can not be empty!");
		}
		if (productForm.getProducer() == null) {
			errors.rejectValue("producer", "", "Select producer!");
		}
		if (productForm.getProductType() == null) {
			errors.rejectValue("productType", "", "Select product type!");
		}
		// if (productForm.getId() == 0) if (errors.getFieldError("producer") ==
		// null
		// && errors.getFieldError("productType") == null
		// && productService.findProduct(productForm) != null) {
		// errors.rejectValue("error", "", "Product already exists!");
		// }
		if (productForm.getId() == 0) if(errors.getFieldError("price")==null)
			if (productService.findProduct(productForm) != null) {
				errors.rejectValue("error", "", "Product already exists!");
			}

		Matcher m = price.matcher(productForm.getPrice());

		if (errors.getFieldError("price") == null && !m.matches()) {
			errors.rejectValue("price", "", "Incorrect price format!");
		}

	}

}
