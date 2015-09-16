package com.tracker.web.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
	
	@Override
	public void initialize(Email constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email.substring(email.indexOf('@')+1, email.length()).equalsIgnoreCase("rit.edu"))
			return true;
		else
			return false;
	}

}
