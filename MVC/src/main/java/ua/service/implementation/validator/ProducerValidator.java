package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Producer;
import ua.service.ProducerService;

public class ProducerValidator implements Validator {

	private final ProducerService producerService;

	public ProducerValidator(ProducerService producerService) {
		this.producerService = producerService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Producer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Producer producer = (Producer) target;
		if (producerService.findByName(producer.getName()) != null) {
			errors.rejectValue("name", "", "Producer already exists!");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can not be empty!");
	}

}
