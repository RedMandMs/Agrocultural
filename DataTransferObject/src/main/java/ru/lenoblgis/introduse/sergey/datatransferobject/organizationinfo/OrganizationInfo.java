package ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo;

import java.io.Serializable;

public class OrganizationInfo implements Serializable{
	
	
	
	/**
	 * ����������� ��-���������
	 */
	public OrganizationInfo() {
		
	}

	/**
	 * ����������� ��� ����������� ��� ���������� ��������� � ��
	 * @param id - id ��������
	 * @param name - ��� �����������
	 * @param inn - ����� ��� �����������
	 * @param address - ����� �����������
	 */
	public OrganizationInfo(int id, String name, int inn, String address) {
		this.id = id;
		this.name = name;
		this.inn = inn;
		setAddress(address);
	}
	
	/**
	 * ����������� ��� ����������� � �� ��� �� ������������ �����������
	 * @param name - ��� �����������
	 * @param inn - ����� ��� �����������
	 * @param address - ����� �����������
	 */
	public OrganizationInfo(String name, int inn, String address) {
		this.name = name;
		this.inn = inn;
		setAddress(address);
	}



	/**
	 * id �����������
	 */
	private int id;
	
	/**
	 * �������� �����������
	 */
	private String name;
	
	
	/**
	 * ��� �����������
	 */
	private int inn;
	
	
	/**
	 * ������ �����������
	 */
	private String address;


	/**
	 * �������� id �����������
	 * @return - id �����������
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * ���������� id �����������
	 * @param id - ����� �������� id �����������
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * �������� ��� �����������
	 * @return - ��� �����������
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���������� ��� �����������
	 * @param name - ����� ��� �����������
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �������� ��� �������������
	 * @return - ��� �����������
	 */
	public int getInn() {
		return inn;
	}

	/**
	 * ���������� ��� �������������
	 * @param inn - ����� ��� �����������
	 */
	public void setInn(int inn) {
		this.inn = inn;
	}

	/**
	 * �������� ����� �����������
	 * @return - ����� �����������
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * ���������� ����� �����������
	 * @param address - ����� ����� �����������
	 */
	public void setAddress(String address) {
		if(address == null || address.trim() == ""){
			this.address = "UNKNOWN";
		}else{
			this.address = address;			
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrganizationInfo that = (OrganizationInfo) o;

		if (id == that.id) return true;
		if (inn != that.inn) return false;
		return name.equals(that.name);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + inn;
		return result;
	}
}
