package ru.lenoblgis.introduse.sergey.domen.owner;

import java.io.Serializable;


public interface Owner extends Serializable{

	/**
	 * �������� id ���������
	 */
	int getId();
	
	/**
	 * ���������� id ���������
	 */
	public void setId(int id);
	
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
	public void setInn(int iNN);

	/**
	 * �������� ����� ���������
	 */
	public String getAddress();
	/**
	 * ���������� ����� ���������
	 */
	public void setAddres(String addres);

	
}
