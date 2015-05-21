package ru.lenoblgis.introduse.sergey.testing.accessdb;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.data.domen.actionevent.PassportEvent;
import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;

/**
 * Тестирование добавления события в базу данных
 * @author Vilgodskiy
 *
 */
public class TestEventDAO {
	
	/**
	 * Создание паспорта
	 */
	@Test
	@Ignore
	public void testAddEventCreateToDB() {
		DAO dao = new DAO();
		
		Passport passport = new Passport(574, 8, "Всеволожский р-н", "13", 25, "Фермерское хозяйство", null);
		dao.createPassport(passport);
		System.out.println();
	}
	
	/**
	 * Удаление паспорта
	 */
	@Test
	@Ignore
	public void testAddEventDeleteToDB() {
		DAO dao = new DAO();
		
		dao.deletePassport(27);
		System.out.println();
	}
	
	/**
	 * Редактирование паспорта
	 */
	@Test
	@Ignore
	public void testAddEventEditToDB() {
		DAO dao = new DAO();
		
		Passport passport = new Passport(25, 8, "Приземский р-н", "14", 25, "Фермерское хозяйство", "New Comment");
		dao.editPassport(passport);
		System.out.println();
	}
	
	/**
	 * Удаления события
	 */
	@Test
	@Ignore
	public void testDeleteEventToDB(){
		
		DAO dao = new DAO();
		dao.deletePassportEvent(6);
	}
	
	/**
	 * Просмотр всех событий
	 */
	@Test
	@Ignore
	public void testReviewAllEvents(){
		DAO dao = new DAO();
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = dao.reviewAllPassportEvent();
		Assert.assertEquals(8, events.size());
	}
	
	/**
	 * Просмотр событий одного владельца
	 */
	@Test
	@Ignore
	public void testReviwAllOwnersEvents() {
		DAO dao = new DAO();
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = dao.reviewAllOwnerEvents(8);
		Assert.assertEquals(events.size(), 8);
	}
}
