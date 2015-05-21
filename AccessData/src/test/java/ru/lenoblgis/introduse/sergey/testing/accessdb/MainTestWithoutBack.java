package ru.lenoblgis.introduse.sergey.testing.accessdb;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.AdminSpringDAO;
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
		AdminSpringDAO adminDao = new AdminSpringDAO();
		
		Owner newOwner = new Organization("LenOblGis", 1, "Hi");		
		adminDao.createOwner(newOwner);;
	}
	
	/**
	 * Тестирование создания пасспорта
	 */
	@Test
	@Ignore
	public void testCreatePassport(){
		AdminSpringDAO adminDao = new AdminSpringDAO();
		
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
		AdminSpringDAO adminDao = new AdminSpringDAO();
		adminDao.reviewPassport(12);		
	}

}
