package ru.lenoblgis.introduse.sergey.verefication.implimentation.passport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.verefication.annotation.passport.NewCadastrNumberPassport;

public class NewCadastrNumberPassportValidator implements ConstraintValidator<NewCadastrNumberPassport, Passport>{

	@Override
	public void initialize(NewCadastrNumberPassport arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Passport arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
