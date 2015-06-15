package ru.lenoblgis.introduse.sergey.verefication.implimentation.organization;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewINNOrganization;

public class NewINNOrganizationValidator implements ConstraintValidator<NewINNOrganization, Owner>{

	@Override
	public void initialize(NewINNOrganization anotation) {
		
	}

	@Override
	public boolean isValid(Owner organization, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
