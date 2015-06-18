package ru.lenoblgis.introduse.sergey.validation.implementation.organization;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.validation.annotation.organization.NonCopyNameOrganization;

public class NonCopyNameOrganizationValidator implements ConstraintValidator<NonCopyNameOrganization, String> {

	DAO dao = new DAO();
	
	@Override
	public void initialize(NonCopyNameOrganization arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext arg1) {
		if(name == null || (name.trim().equals(""))){
			return true;
		}
		Organization organization = dao.findOwnerByName(name);
		if(organization == null){
			return true;
		}
		return false;
	}

}
