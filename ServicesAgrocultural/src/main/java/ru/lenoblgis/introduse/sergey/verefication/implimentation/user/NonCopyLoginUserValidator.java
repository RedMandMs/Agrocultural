package ru.lenoblgis.introduse.sergey.verefication.implimentation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.verefication.annotation.user.NonCopyLoginUser;

public class NonCopyLoginUserValidator implements ConstraintValidator<NonCopyLoginUser, User>{

	@Override
	public void initialize(NonCopyLoginUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(User arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
