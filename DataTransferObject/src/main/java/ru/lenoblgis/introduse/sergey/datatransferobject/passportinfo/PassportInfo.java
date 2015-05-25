package ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo;

import java.io.Serializable;


public class PassportInfo implements Serializable{

	
	/**
	 * ����������� ��� ����������� �������� �� ��
	 * @param id - id ���������
	 * @param idOwner - id ���������
	 * @param region - ������
	 * @param cadastrNumber - ����������� �����
	 * @param area - �������
	 * @param type - ��� ����
	 * @param comment - �����������
	 */
	public PassportInfo(int id, int idOwner, String region, String nameOwner,
			Integer cadastrNumber, double area, String type, String comment) {
		this.id = id;
		this.idOwner = idOwner;
		this.nameOwner = nameOwner;
		this.region = region;
		this.cadastrNumber = cadastrNumber;
		this.area = area;
		this.type = type;
		this.comment = comment;
	}

	/**
	 * ����������� ��� ������ ��������� � �� (��� id)
	 * @param idOwner - id ���������
	 * @param region - ������
	 * @param cadastrNumber - ����������� �����
	 * @param area - �������
	 * @param type - ��� ����
	 * @param comment - �����������
	 */
	public PassportInfo(int idOwner, String region, Integer cadastrNumber,
			double area, String type, String comment) {
		this.idOwner = idOwner;
		this.region = region;
		this.cadastrNumber = cadastrNumber;
		this.area = area;
		this.type = type;
		this.comment = comment;
	}


	/**
	 * Id ����
	 */
	private int id;
	
	 /**
	  * �����������-�������� ����
	  */
	private int idOwner;
	
	/**
	 * ��� ��������� ���������
	 */
	private String nameOwner;
	
	/**
	 * ������� ������������ ����
	 */
	private String region;
	
	/**
	 * ����������� ����� ����
	 */
	private Integer cadastrNumber;
	
	/**
	 * ������� ����
	 */
	private double area;
	
	/**
	 * ��� ����
	 */
	private String type;
	
	/**
	 * ����������� � ����
	 */
	private String comment;
	
	
	/**
	 * ��������� id ��������
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * ��������� id ��������
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * ��������� id ���������
	 */
	public int getIdOwner() {
		return idOwner;
	}
	
	/**
	 * ��������� id ���������
	 */
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	
	/**
	 * ��������� �������
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * ��������� �������
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	/**
	 * ��������� ������������ ������
	 */
	public Integer getCadastrNumber() {
			return cadastrNumber;
	}
	
	/**
	 * ��������� ������������ ������
	 */
	public void setCadastrNumber(String cadastrNumber) {
		if(cadastrNumber == null || cadastrNumber.trim().equals("")){
			this.cadastrNumber = null;
		}else{
			this.cadastrNumber = Integer.valueOf(cadastrNumber);
		}
	}
	
	/**
	 * ��������� ������� ����
	 */
	public double getArea() {
		return area;
	}
	
	/**
	 * ��������� ������� ����
	 */
	public void setArea(double area) {
		this.area = area;
	}
	
	/**
	 * ��������� ���� ����
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * ��������� ���� ����
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * ��������� ����������� � ���������
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * ��������� ����������� � ���������
	 */
	public void setComment(String comment) {
		if(comment == null || comment.trim().equals("")){
			this.comment = "This passport hasn't comment";
		}else{
			this.comment = comment;
		}
	}

	/**
	 * ��������� ����� ���������
	 * @return - ��� ���������
	 */
	public String getNameOwner() {
		return nameOwner;
	}

	/**
	 * ��������� ����� ���������
	 * @param nameOwner - ����� ��� ���������
	 */
	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}
	
}
