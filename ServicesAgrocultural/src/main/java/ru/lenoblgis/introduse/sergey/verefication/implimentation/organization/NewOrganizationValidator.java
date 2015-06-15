package ru.lenoblgis.introduse.sergey.verefication.implimentation.organization;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewOrganization;

public class NewOrganizationValidator implements ConstraintValidator<NewOrganization, Owner>{

	@Override
	public void initialize(NewOrganization anotation) {
		
	}

	@Override
	public boolean isValid(Owner organization, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
