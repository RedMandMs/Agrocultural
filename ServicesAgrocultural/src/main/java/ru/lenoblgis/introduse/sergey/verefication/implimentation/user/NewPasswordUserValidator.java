package ru.lenoblgis.introduse.sergey.verefication.implimentation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.verefication.annotation.user.NewPasswordUser;

public class NewPasswordUserValidator implements ConstraintValidator<NewPasswordUser, User>{

	@Override
	public void initialize(NewPasswordUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(User arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
