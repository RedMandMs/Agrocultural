package ru.lenoblgis.introduse.sergey.validation.implementation.passport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.validation.annotation.passport.PositiveArea;

public class PositiveAreaValidator implements ConstraintValidator<PositiveArea, Float>{

	DAO dao = new DAO();
	
	@Override
	public void initialize(PositiveArea arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(Float area, ConstraintValidatorContext context) {
		if(area != null && area > 0){
				return true;
			}
		return false;
	}
	
}
