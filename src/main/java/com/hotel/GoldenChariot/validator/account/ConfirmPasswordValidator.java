package com.hotel.GoldenChariot.validator.account;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    private String password;

    private String confirmPassword;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);

        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext){

        String passwordValue = new BeanWrapperImpl(obj).getPropertyValue(password).toString();
        String confirmPasswordValue = new BeanWrapperImpl(obj).getPropertyValue(confirmPassword).toString();

        if (passwordValue.equals(confirmPasswordValue)){
            return true;
        }

        return false;

    }
}
