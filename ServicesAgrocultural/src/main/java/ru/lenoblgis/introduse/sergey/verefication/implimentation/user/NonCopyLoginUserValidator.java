package ru.lenoblgis.introduse.sergey.verefication.implimentation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.verefication.annotation.user.NonCopyLoginUser;

public class NonCopyLoginUserValidator implements ConstraintValidator<NonCopyLoginUser, User>{

	@Autowired
	DAO dao;
	
	@Override
	public void initialize(NonCopyLoginUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext arg1) {
		if(user != null){
			User findingUser = dao.findUserByLogin(user.getLogin());
			if(findingUser == null){
				return true;
			}
		}
		return false;
	}

}
