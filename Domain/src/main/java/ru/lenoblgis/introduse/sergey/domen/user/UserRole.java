package ru.lenoblgis.introduse.sergey.domen.user;

import java.io.Serializable;

public enum UserRole implements Serializable{
	
	//Неизвестна роль
	UNKNOWN("UNKNOWN"),
	//Зарегестрированный пользователь
	USER("USER"),
	//Администратор
	ADMIN("ADMIN"),
	//Гость
	GUEST("GUEST");
	

	/**
	 * Конструктор с имененм роли
	 * @param name - имя роли
	 */
	private UserRole(String name) {
		this.name = name;
	}

	/**
	 * Имя роли
	 */
	private String name;

	/**
	 * Получить имя роли
	 * @return - имя роли
	 */
	public String getName() {
		return name;
	}
	
}
