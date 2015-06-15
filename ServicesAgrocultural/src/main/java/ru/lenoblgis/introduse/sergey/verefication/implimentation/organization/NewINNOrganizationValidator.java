package ru.lenoblgis.introduse.sergey.verefication.implimentation.organization;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewINNOrganization;

public class NewINNOrganizationValidator implements ConstraintValidator<NewINNOrganization, Owner>{

	@Autowired
	DAO dao;
	
	@Override
	public void initialize(NewINNOrganization anotation) {
		
	}

	@Override
	public boolean isValid(Owner organization, ConstraintValidatorContext context) {
		if(organization != null){
			if(organization.getInn() != null){
				List<Organization> listOrganizations = dao.findOwnerByINN(organization.getInn());
				if(listOrganizations.isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

}
