package ru.lenoblgis.introduse.sergey.domen.owner;

import java.io.Serializable;


public interface Owner extends Serializable{

	/**
	 * Получить id владельца
	 */
	int getId();
	
	/**
	 * Установить id владельца
	 */
	public void setId(int id);
	
	/**
	 * Получить имя владельца
	 */
	public String getName();
	
	/**
	 * Установить имя владельца
	 */
	public void setName(String name);

	/**
	 * Получить ИНН владельца
	 */
	public Integer getInn();
	/**
	 * Установить ИНН владельца
	 */
	public void setInn(int iNN);

	/**
	 * Получить адрес владельца
	 */
	public String getAddress();
	/**
	 * Установить адрес владельца
	 */
	public void setAddres(String addres);

	
}
