package ru.lenoblgis.introduse.sergey.validation.implementation.passport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.validation.annotation.passport.PossitiveCadastrNumber;

public class PossitiveCadastrNumberValidator implements ConstraintValidator<PossitiveCadastrNumber, Integer>{

	@Override
	public void initialize(PossitiveCadastrNumber constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Integer cadastrNumber, ConstraintValidatorContext context) {
		if(cadastrNumber == null){
			return true;
		}else{
			if(cadastrNumber > 0){
				return true;
			}
		}
		return false;
	}

}
