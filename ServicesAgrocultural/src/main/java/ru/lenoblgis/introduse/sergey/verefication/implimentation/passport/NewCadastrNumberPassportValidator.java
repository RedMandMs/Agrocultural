package ru.lenoblgis.introduse.sergey.verefication.implimentation.passport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.verefication.annotation.passport.NewCadastrNumberPassport;

public class NewCadastrNumberPassportValidator implements ConstraintValidator<NewCadastrNumberPassport, Passport>{

	@Autowired
	DAO dao;
	
	@Override
	public void initialize(NewCadastrNumberPassport arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Passport passport, ConstraintValidatorContext context) {
		if(passport != null){
			if(passport.getCadastrNumber() != null){
				Map<String, Object> info = new HashMap<String, Object>();
				info.put("cadastrNumber", passport.getCadastrNumber());
				List<Passport> listPassport = dao.findPassports(info);
				if(listPassport.isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

}
