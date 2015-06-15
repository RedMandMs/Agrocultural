package ru.lenoblgis.introduse.sergey.verefication.implimentation.organization;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewNameOrganization;

public class NewNameOrganizationValidator implements ConstraintValidator<NewNameOrganization, Owner> {

	@Override
	public void initialize(NewNameOrganization arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Owner organization, ConstraintValidatorContext arg1) {
		
		return false;
	}

}
