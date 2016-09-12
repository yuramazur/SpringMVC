package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Carrier;
import ua.service.CarrierService;

public class CarrierValidator implements Validator {
	private final CarrierService carrierService;

	

	public CarrierValidator(CarrierService carrierService) {
		
		this.carrierService = carrierService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Carrier.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Carrier carrier = (Carrier) target;
		if(carrierService.findByName(carrier.getName())!=null){
			errors.rejectValue("name", "", "Carrier olready exists!");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can not be empty!");
	}

}
