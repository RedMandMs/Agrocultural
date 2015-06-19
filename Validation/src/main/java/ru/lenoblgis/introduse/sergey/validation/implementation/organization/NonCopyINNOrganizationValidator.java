package ru.lenoblgis.introduse.sergey.validation.implementation.organization;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.validation.annotation.organization.NonCopyINNOrganization;

public class NonCopyINNOrganizationValidator implements ConstraintValidator<NonCopyINNOrganization, Integer>{

	
	DAO dao = new DAO();
	
	@Override
	public void initialize(NonCopyINNOrganization anotation) {
		
	}

	@Override
	public boolean isValid(Integer inn, ConstraintValidatorContext context) {
			if(inn == null){
				return true;
			}else{
				Organization organization = new Organization();
				organization.setInn(inn);
				List<Organization> listOrganizations = dao.findOwners(organization);
				if(listOrganizations.isEmpty()){
					return true;
				}
			}
		return false;
	}

}
