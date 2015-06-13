package ru.lenoblgis.introduse.sergey.domen.passport;

import java.io.Serializable;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;


public class Passport implements Serializable {
	
	/**
	 * Конструктор для отображения паспорта из БД
	 * @param id - id пасспорта
	 * @param idOwner - id владельца
	 * @param region - регион
	 * @param cadastrNumber - кадастровый номер
	 * @param area - площадь
	 * @param type - тип поля
	 * @param comment - комментарий
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
	 * Конструктор для записи пасспорта в БД (без id)
	 * @param idOwner - id владельца
	 * @param region - регион
	 * @param cadastrNumber - кадастровый номер
	 * @param area - площадь
	 * @param type - тип поля
	 * @param comment - комментарий
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
	 * Конструктор по-умолчанию
	 */
	public Passport() {
		this.region = RegionField.UNKNOWN;
		setComment(null);
		setCadastrNumber(null);
	}
	
	
	/**
	 * Id поля
	 */
	private int id;
	
	 /**
	  * Организация-владелец поля
	  */
	private int idOwner;
	
	/**
	 * Ссылка на владельца пасспорта
	 */
	private Owner owner;
	
	/**
	 * Регионр расположения поля
	 */
	private RegionField region;
	
	/**
	 * Кадастровый номер поля
	 */
	private Integer cadastrNumber;
	
	/**
	 * Площадь поля
	 */
	private float area;
	
	/**
	 * Тип поля
	 */
	private TypeField type;
	
	/**
	 * комментарий к полю
	 */
	private String comment;
	
	
	/**
	 * Получение id паспорта
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * установка id паспорта
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Получение id владельца
	 */
	public int getIdOwner() {
		return idOwner;
	}
	
	/**
	 * Установка id владельца
	 */
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	
	/**
	 * Получение региона
	 */
	public String getRegion() {
		return region.getRegion();
	}
	
	/**
	 * Установка региона
	 */
	public void setRegion(String region) {
		this.region = RegionField.getRegion(region);
	}
	
	/**
	 * Получение кадастрового номера
	 */
	public Integer getCadastrNumber() {
			return cadastrNumber;
	}
	
	/**
	 * Установка кадастрового номера
	 */
	public void setCadastrNumber(String cadastrNumber) {
		if(cadastrNumber == null || cadastrNumber.trim().equals("")){
			this.cadastrNumber = null;
		}else{
			this.cadastrNumber = Integer.valueOf(cadastrNumber);
		}
	}
	
	/**
	 * Установка кадастрового номера
	 */
	public void setCadastrNumber(int cadastrNumber) {
		this.cadastrNumber = cadastrNumber;
	}
	
	/**
	 * Получение площади поля
	 */
	public float getArea() {
		return area;
	}
	
	/**
	 * Установка площади поля
	 */
	public void setArea(float area) {
		this.area = area;
	}
	
	/**
	 * Получение типа поля
	 */
	public String getType() {
		return type.getType();
	}
	
	/**
	 * Установка типа поля
	 */
	public void setType(String type) {
		this.type = TypeField.getTypeOf(type);
	}
	
	/**
	 * Получение комментария к пасспорту
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
	 * Установка комментария к пасспорту
	 */
	public void setComment(String comment) {
		if(comment == null || comment.trim().equals("")){
			this.comment = "К данному паспорту нету комментариев";
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
