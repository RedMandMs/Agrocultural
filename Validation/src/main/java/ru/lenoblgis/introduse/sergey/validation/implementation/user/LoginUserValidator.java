package ru.lenoblgis.introduse.sergey.validation.implementation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.validation.annotation.user.LoginUser;

public class LoginUserValidator implements ConstraintValidator<LoginUser, String>{
	
	@Override
	public void initialize(LoginUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		if(login != null ){
			if(login.length() < 16 && login.trim().length() > 4){
				return true;
			}
		}
		return false;
	}

}
