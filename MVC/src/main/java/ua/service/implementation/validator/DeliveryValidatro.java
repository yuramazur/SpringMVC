package ua.service.implementation.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Delivery;
import ua.service.DeliveryService;

public class DeliveryValidatro implements Validator {

	private final DeliveryService deliveryService;
	
	private static final Pattern numCarrDep = Pattern.compile("^[^0][0-9]{1,3}&");

	public DeliveryValidatro(DeliveryService deliveryService) {

		this.deliveryService = deliveryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return Delivery.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Delivery delivery = (Delivery) target;

		if (delivery.getCity() == null) {
			errors.rejectValue("city", "", "Select city!");
		}
		if (delivery.getCarrier() == null) {
			errors.rejectValue("carrier", "", "Select carrier!");
		}
		if (errors.getFieldError("city") == null
				&& errors.getFieldError("carrier") == null
				&& deliveryService.findDelivery(delivery) != null) {
			errors.rejectValue("error", "", "Delivery already exists!");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numCerrDep", "", "Can not be empty!");
	}
}
