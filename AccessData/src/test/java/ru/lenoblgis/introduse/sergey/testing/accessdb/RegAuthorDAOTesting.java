package ru.lenoblgis.introduse.sergey.testing.accessdb;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.user.User;

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
		dao.createUser("Sergey", "lenoblgis", 30);
	}
	
	
	/**
	 * ����������� ������������
	 */
	@Test
	public void testReviewUser(){
		DAO dao = new DAO();
		User user = dao.reviewUser("Sergey", "lenoblgis");
		Assert.assertEquals(null, user);
		System.out.println();
	}
	
	
	
}
