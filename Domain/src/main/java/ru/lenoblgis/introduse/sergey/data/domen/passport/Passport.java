package ru.lenoblgis.introduse.sergey.data.domen.passport;

import java.io.Serializable;

import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;


public class Passport implements Serializable {
	
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
	public Passport(int id, int idOwner, String region, String cadastrNumber,
			int area, String type, String comment) {
		setID(id);
		setIdOwner(idOwner);
		setRegion(region);
		setCadastrNumber(cadastrNumber);
		setArea(area);
		setType(type);		
		setComment(comment);
		setOwner(owner);
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
	public Passport(int idOwner, String region, String cadastrNumber,
			int area, String type, String comment, Owner owner) {
		setIdOwner(idOwner);
		setRegion(region);
		setCadastrNumber(cadastrNumber);
		setArea(area);
		setType(type);		
		setComment(comment);
		setOwner(owner);
	}
	
	/**
	 * ����������� ��-���������
	 */
	public Passport() {
		this.region = RegionField.UNKNOWN;
		setComment(null);
		setCadastrNumber(null);
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
	 * ������ �� ��������� ���������
	 */
	private Owner owner;
	
	/**
	 * ������� ������������ ����
	 */
	private RegionField region;
	
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
	private TypeField type;
	
	/**
	 * ����������� � ����
	 */
	private String comment;
	
	
	/**
	 * ��������� id ��������
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * ��������� id ��������
	 */
	public void setID(int id) {
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
		return region.getRegion();
	}
	
	/**
	 * ��������� �������
	 */
	public void setRegion(String region) {
		this.region = RegionField.getRegion(region);
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
		return type.getType();
	}
	
	/**
	 * ��������� ���� ����
	 */
	public void setType(String type) {
		this.type = TypeField.getType(type);
	}
	
	/**
	 * ��������� ����������� � ���������
	 */
	public String getComment() {
		return comment;
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
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



}
