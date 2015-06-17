package ru.lenoblgis.introduse.sergey.validation.implementation.organization;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.validation.annotation.organization.NameCompany;

public class NameCompanyValidator implements ConstraintValidator<NameCompany, String>{

	@Override
	public void initialize(NameCompany anotation) {
		
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if(name != null){
			if(name.length() > 2 && name.length() < 21){
				return true;
			}
		}
		return false;
	}

}
