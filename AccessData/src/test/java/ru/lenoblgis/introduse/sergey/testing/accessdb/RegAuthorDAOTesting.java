package ru.lenoblgis.introduse.sergey.testing.accessdb;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.data.domen.owner.User;

/**
 * ������������ ������ � �������� ������������� � ��
 * @author ������
 *
 */
public class RegAuthorDAOTesting {

	/**
	 * �������� ������������ � ���� ������
	 */
	@Test
	@Ignore
	public void testCreatingUser(){
		DAO dao = new DAO();
		dao.registration("Sergey", "lenoblgis", 30);
	}
	
	
	/**
	 * ����������� ������������
	 */
	@Test
	public void testReviewUser(){
		DAO dao = new DAO();
		User user = dao.authorization("Sergey", "lenoblgis");
		Assert.assertEquals(null, user);
		System.out.println();
	}
	
	
	
}
