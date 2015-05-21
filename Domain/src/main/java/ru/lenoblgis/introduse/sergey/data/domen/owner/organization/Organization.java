package ru.lenoblgis.introduse.sergey.data.domen.owner.organization;

import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;

public class Organization implements Owner{
	
	/**
	 * �����������, ������������ ��� �����������
	 * @param id - id �����������
	 * @param name - ��� �����������
	 * @param inn - ��� �����������
	 * @param addres - ����� ������������ �����������
	 */
	public Organization(int id, String name, int inn, String addres) {
		this.id = id;
		this.name = name;
		this.inn = inn;
		if(addres == null || addres.trim().equals("")){
			this.address = "UNKNOWN";
		}else{
			this.address = addres;
		}
	}
	
	/**
	 * ����������� ��� ������ ����������� � �� (��� id)
	 * @param name
	 * @param inn
	 * @param addres
	 */
	public Organization(String name, int inn, String addres) {
		this.name = name;
		this.inn = inn;
		if(addres == null || addres.trim().equals("")){
			this.address = "UNKNOWN";
		}else{
			this.address = addres;
		}
	}
	
	/**
	 * ����������� ��-���������
	 */
	public Organization(){
		this.address = "UNKNOWN";
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
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#getId()
	 */
	public int getId() {
		return id;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#getINN()
	 */
	public int getINN() {
		return inn;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#setINN(int)
	 */
	public void setINN(int iNN) {
		inn = iNN;
	}

	/**
	 * @see dataTier.domenModel.owner.Owner#getAddres()
	 */
	public String getAddres() {
		return address;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Owner#setAddres(java.lang.String)
	 */
	public void setAddres(String addres) {
		this.address = addres;
	}

}
