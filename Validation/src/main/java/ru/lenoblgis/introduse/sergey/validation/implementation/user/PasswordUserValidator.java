package ru.lenoblgis.introduse.sergey.validation.implementation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.validation.annotation.user.PasswordUser;

public class PasswordUserValidator implements ConstraintValidator<PasswordUser, String>{

	@Override
	public void initialize(PasswordUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext arg1) {
		if(password != null){
			if(password.length() < 16 && password.trim().length() > 4){
				return true;
			}
		}
		return false;
	}

}
