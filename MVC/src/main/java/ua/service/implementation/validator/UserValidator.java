package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.UserForm;

import ua.service.UserService;

public class UserValidator implements Validator {
	private final UserService userService;

	private static final Pattern mail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	private static final Pattern phone = Pattern.compile("^[0-9]+$");

	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return UserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "",
				"Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
				"Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm",
				"", "Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "",
				"Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "",
				"Can not be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "",
				"Can not be empty!");

		if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			errors.rejectValue("password", "", "password dosn't match!");
			errors.rejectValue("passwordConfirm", "", "password dosn't match!");
		}
		if (userService.findByLogin(userForm.getLogin()) != null) {
			errors.rejectValue("login", "", "User allready exists!");
		}
		if (userService.findByMail(userForm.getMail()) != null) {
			errors.rejectValue("mail", "", "E-Mail allready exists!");
		}
		if (userService.findByPhone(userForm.getPhone()) != null) {
			errors.rejectValue("phone", "", "Phone allready exists!");
		}
		Matcher m = mail.matcher(userForm.getMail());
		Matcher p = phone.matcher(userForm.getPhone());
		if(!m.matches()){
			errors.rejectValue("mail", "", "Incorect E-Mail format!");
		}
		if(!p.matches()){
			errors.rejectValue("phone", "", "Incore phone format((xxx)xxx-xxxx or xxxxxxxxxx)");
		}
	}

}
