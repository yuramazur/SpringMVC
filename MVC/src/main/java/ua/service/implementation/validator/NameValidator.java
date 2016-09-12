package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Name;
import ua.service.NameService;

public class NameValidator implements Validator {

	private final NameService nameService;

	public NameValidator(NameService nameService) {
		
		this.nameService = nameService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Name.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Name name = (Name) target;
		if (nameService.findByNames(name.getNames()) != null) {
			errors.rejectValue("names", "", "Name olready exists!");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "names", "",
				"Can not be empty!");
	}

}
