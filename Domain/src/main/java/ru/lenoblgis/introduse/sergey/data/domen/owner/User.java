package ru.lenoblgis.introduse.sergey.data.domen.owner;

public class User {
	
	/**
	 * id пользовател€
	 */
	private int id;
	
	/**
	 * Ћогин пользовател€
	 */
	private String userLogin;

	/**
	 * ѕароль пользовател€
	 */
	private String userPassword;
	
	/**
	 * id организации, которой владеет пользователь
	 */
	private int organizationId;
	
		
	
	/**
	 *  онструктор по-умолчанию
	 */
	public User() {
	}
	
	/**
	 *  онструктор используемый помещении нового пользовател€ в Ѕƒ
	 * @param id - id пользовател€
	 * @param userLogin - логин пользовател€
	 * @param userPassword - пароль пользовател€
	 * @param organizationId - id организации, владельцем, которой €в-с€ пользователь
	 */
	public User(String userLogin, String userPassword, int organizationId) {
		super();
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.organizationId = organizationId;
	}

	/**
	 *  онструктор используемый при выборке пол€ из Ѕƒ
	 * @param id - id пользовател€
	 * @param userLogin - логин пользовател€
	 * @param userPassword - пароль пользовател€
	 * @param organizationId - id организации, владельцем, которой €в-с€ пользователь
	 */
	public User(int id, String userLogin, String userPassword,
			int organizationId) {
		super();
		this.id = id;
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.organizationId = organizationId;
	}


	/**
	 * ѕолучить id пользовател€
	 * @return - id пользовател€
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * ”становить id пользовател€
	 * @param id - новый id пользовател€
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * ѕолучить логин пользовател€
	 * @return - логин пользовател€
	 */
	public String getUserLogin() {
		return userLogin;
	}
	
	/**
	 * ”становить логин пользовател€
	 * @param userLogin - новый логин пользовател€
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	/**
	 * ѕолучить пароль пользовател€
	 * @return - пароль пользовател€
	 */
	public String getUserPassword() {
		return userPassword;
	}
	
	/**
	 * ”становить новый пароль пользовател€
	 * @param userPassword - пароль пользовател€
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	/**
	 * ѕолучить id организации, который владеет пользователь
	 * @return - id управл€емой организации
	 */
	public int getOrganizationId() {
		return organizationId;
	}
	
	/**
	 * ”становить id организации, который будет владеть пользователь
	 * @param organizationId - новое id организации
	 */
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

}
