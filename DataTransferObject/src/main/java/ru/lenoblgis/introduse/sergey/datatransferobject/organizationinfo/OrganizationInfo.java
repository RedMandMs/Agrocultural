package ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo;

import java.io.Serializable;

public class OrganizationInfo implements Serializable{
	
	
	
	/**
	 * Конструктор по-умолчанию
	 */
	public OrganizationInfo() {
		
	}

	/**
	 * Конструктор для отображения уже имеющегося пасспорта в БД
	 * @param id - id паспорта
	 * @param name - имя организации
	 * @param inn - номер ИНН организации
	 * @param address - адрес организации
	 */
	public OrganizationInfo(int id, String name, int inn, String address) {
		this.id = id;
		this.name = name;
		this.inn = inn;
		setAddress(address);
	}
	
	/**
	 * Конструктор для отображения в БД ещё не существующей организации
	 * @param name - имя организации
	 * @param inn - номер ИНН организации
	 * @param address - адрес организации
	 */
	public OrganizationInfo(String name, int inn, String address) {
		this.name = name;
		this.inn = inn;
		setAddress(address);
	}



	/**
	 * id организации
	 */
	private int id;
	
	/**
	 * Название организации
	 */
	private String name;
	
	
	/**
	 * ИНН организации
	 */
	private int inn;
	
	
	/**
	 * Адресс организации
	 */
	private String address;


	/**
	 * Получить id организации
	 * @return - id организации
	 */
	public int getId() {
		return id;
	}

	/**
	 * Установить id организации
	 * @param id - новое значение id организации
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Получить имя организации
	 * @return - имя организации
	 */
	public String getName() {
		return name;
	}

	/**
	 * Установить имя организации
	 * @param name - новое имя организации
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Получить ИНН органанизации
	 * @return - ИНН организации
	 */
	public int getInn() {
		return inn;
	}

	/**
	 * Установить ИНН органанизации
	 * @param inn - новый ИНН организации
	 */
	public void setInn(int inn) {
		this.inn = inn;
	}

	/**
	 * Получить адрес организации
	 * @return - адрес организации
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Установить адрес организации
	 * @param addres - новый адрес организации
	 */
	public void setAddress(String address) {
		if(address == null || address.trim() == ""){
			this.address = "UNKNOWN";
		}else{
			this.address = address;			
		}
	}
	
}
