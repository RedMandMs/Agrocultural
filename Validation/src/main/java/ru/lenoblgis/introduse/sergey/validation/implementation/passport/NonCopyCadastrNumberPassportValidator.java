package ru.lenoblgis.introduse.sergey.validation.implementation.passport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.validation.annotation.passport.NonCopyCadastrNumberPassport;

public class NonCopyCadastrNumberPassportValidator implements ConstraintValidator<NonCopyCadastrNumberPassport, Integer>{

	DAO dao = new DAO();
	
	@Override
	public void initialize(NonCopyCadastrNumberPassport arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Integer cadastrNumber, ConstraintValidatorContext context) {
		if(cadastrNumber == null || cadastrNumber == 0){
			return true;
		}
			Map<String, Object> info = new HashMap<String, Object>();
			info.put("cadastr_number", cadastrNumber);
			List<Passport> listPassport = dao.findPassports(info);
			if(listPassport.isEmpty()){
				return true;
		}
		return false;
	}

}
