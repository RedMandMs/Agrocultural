package ru.lenoblgis.introduse.sergey.domen.owner;

import java.io.Serializable;


public interface Owner extends Serializable{

	/**
	 * �������� id ���������
	 */
	Integer getId();
	
	/**
	 * ���������� id ���������
	 */
	public void setId(Integer id);
	
	/**
	 * �������� ��� ���������
	 */
	public String getName();
	
	/**
	 * ���������� ��� ���������
	 */
	public void setName(String name);

	/**
	 * �������� ��� ���������
	 */
	public Integer getInn();
	/**
	 * ���������� ��� ���������
	 */
	public void setInn(Integer iNN);

	/**
	 * �������� ����� ���������
	 */
	public String getAddress();
	/**
	 * ���������� ����� ���������
	 */
	public void setAddres(String addres);

	
}
