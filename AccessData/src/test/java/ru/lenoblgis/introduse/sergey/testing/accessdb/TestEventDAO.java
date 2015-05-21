package ru.lenoblgis.introduse.sergey.testing.accessdb;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.AdminSpringDAO;
import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.data.domen.actionevent.PassportEvent;
import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;

/**
 * ������������ ���������� ������� � ���� ������
 * @author Vilgodskiy
 *
 */
public class TestEventDAO {
	
	/**
	 * �������� ��������
	 */
	@Test
	@Ignore
	public void testAddEventCreateToDB() {
		DAO dao = new AdminSpringDAO();
		
		Passport passport = new Passport(574, 8, "������������ �-�", "13", 25, "���������� ���������", null);
		dao.createPassport(passport);
		System.out.println();
	}
	
	/**
	 * �������� ��������
	 */
	@Test
	@Ignore
	public void testAddEventDeleteToDB() {
		DAO dao = new AdminSpringDAO();
		
		dao.deletePassport(27);
		System.out.println();
	}
	
	/**
	 * �������������� ��������
	 */
	@Test
	@Ignore
	public void testAddEventEditToDB() {
		DAO dao = new AdminSpringDAO();
		
		Passport passport = new Passport(25, 8, "���������� �-�", "14", 25, "���������� ���������", "New Comment");
		dao.editPassport(passport);
		System.out.println();
	}
	
	/**
	 * �������� �������
	 */
	@Test
	@Ignore
	public void testDeleteEventToDB(){
		
		AdminSpringDAO dao = new AdminSpringDAO();
		dao.deletePassportEvent(6);
	}
	
	/**
	 * �������� ���� �������
	 */
	@Test
	@Ignore
	public void testReviewAllEvents(){
		AdminSpringDAO dao = new AdminSpringDAO();
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = dao.reviewAllPassportEvent();
		Assert.assertEquals(8, events.size());
	}
	
	/**
	 * �������� ������� ������ ���������
	 */
	@Test
	@Ignore
	public void testReviwAllOwnersEvents() {
		AdminSpringDAO dao = new AdminSpringDAO();
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = dao.reviewAllOwnerEvents(8);
		Assert.assertEquals(events.size(), 8);
	}
}
