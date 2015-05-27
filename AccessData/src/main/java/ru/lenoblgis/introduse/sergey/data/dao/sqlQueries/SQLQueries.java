package ru.lenoblgis.introduse.sergey.data.dao.sqlQueries;

import java.io.Serializable;
import java.util.Map;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.user.User;

public interface SQLQueries extends Serializable {

	/**
	 * Создание владельца
	 * @param owner 
	 */
	public String createOwner(Owner owner);
	
	/**
	 * Удаление владельца
	 */
	public String deleteOwner();
	
	/**
	 * Редактировать владельца
	 */
	public String editOwner();
	
	/**
	 * Просмотреть владельца
	 */
	public String reviewOwner();
	
	
	/**
	 * Создание паспорта
	 */
	public String createPassport();
	
	/**
	 * Удаление паспорта
	 */
	public String deletePassport();
	
	/**
	 * Редактирование паспорта
	 */
	public String editPassport();
	
	/**
	 * Просмотр паспорта
	*/
	public String reviewPassport();
	
	/**
	 * Просмотр всех пасспортов
	 */
	public String reviewAllPassports();
	
	/**
	 * Поиск паспорта
	 */
	public String findPassports(Map<String,String> info);
	
	/**
	 * Сформировать запрос для вставки события
	 * @return - запрос вставки события пасспорта
	 */
	public String createPassportEvent();
	
	/**
	 * Сформировать запрос для удаления события
	 * @return - запрос удаления события пасспорта
	 */
	public String deletePassportEvent();
	
	/**
	 * Сформировать запрос для выборки всех событий системы
	 * @return - запрос на выборку событий пасспортов
	 */
	public String reviewAllPassportEvent();
	
	/**
	 * Сформировать запрос для выборки событий, осуществляемых выбранным пользователем
	 * @return - запрос на выборку событий пасспортов владельца
	 */
	public String reviwAllOwnerPassportEvent();
	
	/**
	 * Сформировать запрос для получения максимального id паспорта созданного организацией с переданным id
	 * @return - запрос
	 */
	public String getMAXidPassportByOwner();
	
	/**
	 * Сформировать запрос для проверки совпадения логина и пароля пользователя (авторизация)
	 * @return - запрос
	 */
	public String authorization();
	
	/**
	 * Сформировать запрос для создания нового пользователя системы (регистрация)
	 * @return - запрос
	 */
	public String createUser(User user);
}
