package ru.lenoblgis.introduse.sergey.validation.implementation.organization;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.validation.annotation.organization.NonCopyINNOrganization;

public class NewINNOrganizationValidator implements ConstraintValidator<NonCopyINNOrganization, Integer>{

	
	DAO dao = new DAO();
	
	@Override
	public void initialize(NonCopyINNOrganization anotation) {
		
	}

	@Override
	public boolean isValid(Integer inn, ConstraintValidatorContext context) {
			if(inn != null){
				List<Organization> listOrganizations = dao.findOwnerByINN(inn);
				if(listOrganizations.isEmpty()){
					return true;
				}
			}
		return false;
	}

}
