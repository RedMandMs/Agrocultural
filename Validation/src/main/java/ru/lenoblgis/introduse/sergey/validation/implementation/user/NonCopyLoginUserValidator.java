package ru.lenoblgis.introduse.sergey.validation.implementation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.validation.annotation.user.NonCopyLoginUser;

public class NonCopyLoginUserValidator implements ConstraintValidator<NonCopyLoginUser, String>{

	DAO dao = new DAO();
	
	@Override
	public void initialize(NonCopyLoginUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext arg1) {
		if(login != null){
			User findingUser = dao.findUserByLogin(login);
			if(findingUser == null){
				return true;
			}
		}
		return false;
	}
}
