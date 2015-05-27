package ru.lenoblgis.introduse.sergey.testing.accessdb;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.domen.passport.RegionField;
import ru.lenoblgis.introduse.sergey.domen.passport.TypeField;



public class MainTestWithoutBack {
	
	/**
	 * Тестирование создания владельца
	 */
	@Test
	@Ignore
	public void testCreateOwner(){

	}
	
	/**
	 * Тестирование создания пасспорта
	 */
	@Test
	@Ignore
	public void testCreatePassport(){
		DAO adminDao = new DAO();
		Passport newPassport = new Passport(30, RegionField.PRIZEMSK.getRegion(), "21", 30, TypeField.COLLECTIVE_FARM.getType(), "Тест создания пасспорта");
		adminDao.createPassport(newPassport);
	}
	
	/**
	 * Тестирование просмотра пасспорта поля
	 */
	@Test
	@Ignore
	public void testReviewPassport(){
		DAO adminDao = new DAO();
		adminDao.reviewPassport(12);		
	}

}
