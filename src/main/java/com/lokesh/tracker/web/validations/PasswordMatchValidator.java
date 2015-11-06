package com.lokesh.tracker.web.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object>{

	private String password;
	private String repassword;
	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
		password = constraintAnnotation.password();
		repassword = constraintAnnotation.repassword();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try
        {
            final Object firstObj = BeanUtils.getProperty(value, password);
            final Object secondObj = BeanUtils.getProperty(value, repassword);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
        }
        return true;
	}

}
