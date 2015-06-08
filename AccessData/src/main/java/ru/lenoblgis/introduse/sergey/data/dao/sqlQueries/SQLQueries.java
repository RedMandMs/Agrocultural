package ru.lenoblgis.introduse.sergey.data.dao.sqlQueries;

import java.io.Serializable;
import java.util.Map;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.domen.user.User;

public interface SQLQueries extends Serializable {

	/**
	 * —оздание владельца
	 * @param owner 
	 */
	public String createOwner(Owner owner);
	
	/**
	 * ”даление владельца
	 */
	public String deleteOwner();
	
	/**
	 * –едактировать владельца
	 */
	public String editOwner();
	
	/**
	 * ѕросмотреть владельца
	 */
	public String reviewOwner();
	
	
	/**
	 * —оздание паспорта
	 */
	public String createPassport(Passport passport);
	
	/**
	 * ”даление паспорта
	 */
	public String deletePassport();
	
	/**
	 * –едактирование паспорта
	 */
	public String editPassport();
	
	/**
	 * ѕросмотр паспорта
	*/
	public String reviewPassport();
	
	/**
	 * ѕросмотр всех пасспортов
	 */
	public String reviewAllPassports();
	
	/**
	 * ѕоиск паспорта
	 */
	public String findPassports(Map<String,Object> info);
	
	/**
	 * —формировать запрос дл€ вставки событи€
	 * @return - запрос вставки событи€ пасспорта
	 */
	public String createPassportEvent();
	
	/**
	 * —формировать запрос дл€ удалени€ событи€
	 * @return - запрос удалени€ событи€ пасспорта
	 */
	public String deletePassportEvent();
	
	/**
	 * —формировать запрос дл€ выборки всех событий системы
	 * @return - запрос на выборку событий пасспортов
	 */
	public String reviewAllPassportEvent();
	
	/**
	 * —формировать запрос дл€ выборки событий, осуществл€емых выбранным пользователем
	 * @return - запрос на выборку событий пасспортов владельца
	 */
	public String reviwAllOwnerPassportEvent();
	
	/**
	 * —формировать запрос дл€ получени€ максимального id паспорта созданного организацией с переданным id
	 * @return - запрос
	 */
	public String getMAXidPassportByOwner();
	
	/**
	 * —формировать запрос дл€ проверки совпадени€ логина и парол€ пользовател€ (авторизаци€)
	 * @return - запрос
	 */
	public String authorization();
	
	/**
	 * —формировать запрос дл€ создани€ нового пользовател€ системы (регистраци€)
	 * @return - запрос
	 */
	public String createUser(User user);
	
	/**
	 * —формировать запрос дл€ просмотра пользовател€ по логину
	 * @return - запрос
	 */
	public String reviewUserByLogin();
}
