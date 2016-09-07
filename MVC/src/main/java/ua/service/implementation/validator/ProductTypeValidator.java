package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.ProductType;
import ua.service.ProductTypeService;

public class ProductTypeValidator implements Validator {

	private final ProductTypeService productTypeService;

	public ProductTypeValidator(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductType productType = (ProductType) target;
		if (productTypeService.findByName(productType.getName()) != null) {
			errors.rejectValue("name", "", "Product Type already exists!");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can not be empty!");
	}

}
