package ru.lenoblgis.introduse.sergey.validation.implementation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.validation.annotation.user.NewLoginUser;

public class NewLoginUserValidator implements ConstraintValidator<NewLoginUser, String>{
	
	@Override
	public void initialize(NewLoginUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		if(login != null ){
			if(login.length() <= 16 && login.trim().length() > 4){
				return true;
			}
		}
		return false;
	}

}
