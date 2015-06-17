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
	public Passport(Integer id, Integer idOwner, String region, Integer cadastrNumber,
			Float area, String type, String comment) {
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
			Float area, String type, String comment) {
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
	private Integer id;
	
	 /**
	  * �����������-�������� ����
	  */
	private Integer idOwner;
	
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
	private Float area;
	
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
	public void setIdOwner(Integer idOwner) {
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
	public void setCadastrNumber(Integer cadastrNumber) {
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
	public void setArea(Float area) {
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

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cadastrNumber == null) ? 0 : cadastrNumber.hashCode());
		result = prime * result + id;
		result = prime * result + idOwner;
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passport other = (Passport) obj;
		if (id == other.id){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(getClass().getName());
		sb.append("{id=").append(getId());
		sb.append(", idOwner=").append(getIdOwner());
		sb.append(", cadastrNumber=").append(getCadastrNumber());
		sb.append('}');
		return sb.toString();
	}
}
