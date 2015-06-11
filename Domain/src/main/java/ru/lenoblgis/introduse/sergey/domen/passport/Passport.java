package ru.lenoblgis.introduse.sergey.domen.passport;

import java.io.Serializable;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;


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
	public Passport(int id, int idOwner, String region, int cadastrNumber,
			float area, String type, String comment) {
		setId(id);
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
	public Passport(int idOwner, String region, Integer cadastrNumber,
			float area, String type, String comment) {
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
	private float area;
	
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
	 * ��������� ������������ ������
	 */
	public void setCadastrNumber(int cadastrNumber) {
		this.cadastrNumber = cadastrNumber;
	}
	
	/**
	 * ��������� ������� ����
	 */
	public float getArea() {
		return area;
	}
	
	/**
	 * ��������� ������� ����
	 */
	public void setArea(float area) {
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
		this.type = TypeField.getTypeOf(type);
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
			this.comment = "� ������� �������� ���� ������������";
		}else{
			this.comment = comment;
		}
	}



}
