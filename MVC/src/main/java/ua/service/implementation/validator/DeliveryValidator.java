package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.DeliveryForm;
import ua.service.DeliveryService;

public class DeliveryValidator implements Validator {

	private final DeliveryService deliveryService;
	
	private static final Pattern numCarrDep = Pattern.compile("^[1-9]{1,1}([0-9]{1,2})?$");

	public DeliveryValidator(DeliveryService deliveryService) {

		this.deliveryService = deliveryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return DeliveryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DeliveryForm deliveryForm = (DeliveryForm) target;
    
		if (deliveryForm.getCity() == null) {
			errors.rejectValue("city", "", "Select city!");
		}
		if (deliveryForm.getCarrier() == null) {
			errors.rejectValue("carrier", "", "Select carrier!");
		}
		if (errors.getFieldError("city") == null
				&& errors.getFieldError("carrier") == null
				&& deliveryService.findDelivery(deliveryForm) != null) {
			errors.rejectValue("error", "", "Delivery already exists!");
		}
		Matcher m = numCarrDep.matcher(deliveryForm.getNumberDepartment());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberDepartment", "",
				"Can not be empty!");
		if (errors.getFieldError("numberDepartment") == null && !m.matches()) {
			errors.rejectValue("numberDepartment", "", "Incorrect number format!");
		}
	}
}
