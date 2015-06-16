package ru.lenoblgis.introduse.sergey.validation.implementation.passport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.validation.annotation.passport.NewPassport;

public class NewPassportValidator implements ConstraintValidator<NewPassport, Passport>{

	@Override
	public void initialize(NewPassport arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Passport arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
