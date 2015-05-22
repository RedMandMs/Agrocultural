package ru.lenoblgis.introduse.sergey.data.domen.passport;

import java.io.Serializable;

import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;


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
	 * Конструктор для записи пасспорта в БД (без id)
	 * @param idOwner - id владельца
	 * @param region - регион
	 * @param cadastrNumber - кадастровый номер
	 * @param area - площадь
	 * @param type - тип поля
	 * @param comment - комментарий
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
	private double area;
	
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
	public int getID() {
		return id;
	}
	
	/**
	 * установка id паспорта
	 */
	public void setID(int id) {
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
	 * Получение площади поля
	 */
	public double getArea() {
		return area;
	}
	
	/**
	 * Установка площади поля
	 */
	public void setArea(double area) {
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
		this.type = TypeField.getType(type);
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
			this.comment = "This passport hasn't comment";
		}else{
			this.comment = comment;
		}
	}



}
