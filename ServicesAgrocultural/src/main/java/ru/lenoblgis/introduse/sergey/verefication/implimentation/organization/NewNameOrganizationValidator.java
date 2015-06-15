package ru.lenoblgis.introduse.sergey.verefication.implimentation.organization;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.verefication.annotation.organization.NewNameOrganization;

public class NewNameOrganizationValidator implements ConstraintValidator<NewNameOrganization, Owner> {

	@Autowired
	DAO dao;
	
	@Override
	public void initialize(NewNameOrganization arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Owner organization, ConstraintValidatorContext arg1) {
		if(organization!=null){
			if(organization.getName() != null){
				List<Organization> listOrganization = dao.findOwnerByName(organization.getName());
				if(listOrganization.isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

}
