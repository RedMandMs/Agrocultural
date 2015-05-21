package ru.lenoblgis.introduse.sergey.testing.accessdb;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.data.domen.passport.RegionField;
import ru.lenoblgis.introduse.sergey.data.domen.passport.TypeField;



public class MainTestWithoutBack {
	
	/**
	 * Тестирование создания владельца
	 */
	@Test
	@Ignore
	public void testCreateOwner(){
		/*UserSpringDAO userDao = new UserSpringDAO();*/
		DAO adminDao = new DAO();
		
		Owner newOwner = new Organization("LenOblGis", 1, "Hi");		
		adminDao.createOwner(newOwner);;
	}
	
	/**
	 * Тестирование создания пасспорта
	 */
	@Test
	@Ignore
	public void testCreatePassport(){
		DAO adminDao = new DAO();
		
		Owner owner = new Organization("LenOblGis", 1, "Hi"); 
		Passport newPassport = new Passport(8, RegionField.PRIZEMSK.getRegion(), "21", 30, TypeField.COLLECTIVE_FARM.getType(), "Тест создания пасспорта", owner);
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
