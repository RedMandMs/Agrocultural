package ru.lenoblgis.introduse.sergey.validation.implementation.organization;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.validation.annotation.organization.PossitiveINN;

public class PossitiveINNValidator implements ConstraintValidator<PossitiveINN, Integer>{

	@Override
	public void initialize(PossitiveINN constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Integer inn, ConstraintValidatorContext context) {
		if(inn != null){
			if(inn > 0){
				return true;
			}
		}
		return false;
	}

}
