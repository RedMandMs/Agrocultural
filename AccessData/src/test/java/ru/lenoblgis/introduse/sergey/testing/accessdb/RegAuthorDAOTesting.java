package ru.lenoblgis.introduse.sergey.testing.accessdb;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ru.lenoblgis.introduse.sergey.data.dao.AdminSpringDAO;
import ru.lenoblgis.introduse.sergey.data.domen.owner.User;

/**
 * Тестирование работы с таблицей пользователей в БД
 * @author Сергей
 *
 */
public class RegAuthorDAOTesting {

	/**
	 * Создание пользователя в базе данных
	 */
	@Test
	@Ignore
	public void testCreatingUser(){
		AdminSpringDAO dao = new AdminSpringDAO();
		dao.registration("Sergey", "lenoblgis", 30);
	}
	
	
	/**
	 * Авторизация пользователя
	 */
	@Test
	public void testReviewUser(){
		AdminSpringDAO dao = new AdminSpringDAO();
		User user = dao.authorization("Sergey", "lenoblgis");
		Assert.assertEquals(null, user);
		System.out.println();
	}
	
	
	
}
