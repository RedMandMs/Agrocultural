package ru.lenoblgis.introduse.sergey.verefication.implimentation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.verefication.annotation.user.NewLoginUser;

public class NewLoginUserValidator implements ConstraintValidator<NewLoginUser, User>{
	
	@Override
	public void initialize(NewLoginUser arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		if(user != null){
			if(user.getLogin() != null){
				if(user.getLogin().length() <= 16 && user.getLogin().length() > 4){
					return true;
				}
			}
		}
		return false;
	}

}
