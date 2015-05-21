package ru.lenoblgis.introduse.sergey.data.domen.actionevent;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;

public class PassportEvent implements Serializable{
	
	/**
	 * Id события
	 */
	private int  id;
	
	/**
	 * Текстовое сообщение события
	 */
	private String message;
	
	/**
	 * Дата и время события
	 */
	private DateTime dateTime;
	
	/**
	 * Тип события
	 */
	private TypeEvent typeEvent;	
	
	/**
	 * ID автора события
	 */
	private int idAuthor;
	
	/**
	 * Имя автора события (название организации)
	 */
	private String nameAuthor;
	
	/**
	 * Автор события
	 */
	private Owner auther;
	
	/**
	 * ID паспорта, над которым было совершено событие
	 */
	private int idPassport;
	
	/**
	 * Паспорт, над которым было совершено событие
	 */
	private Passport passport;
	
	/**
	 * Конструктор по-умолчанию
	 */
	public PassportEvent(){
		this.typeEvent = TypeEvent.UNKNOWN;
		createMessage();
	}
	
	/**
	 * Конструктор для отображения записей в программное представление
	 * @param id - id события
	 * @param id_passport - id паспорта над которым было совершено событие
	 * @param id_organization - id организации выполнившей действия по данному событию
	 * @param message_event - текстовое сообщение события
	 * @param date_time_event - Дата и время события
	 * @param type_event - тип события
	 */
	public PassportEvent(int id, int id_passport, int id_organization, String message_event, DateTime date_time_event,  String type_event){
		this.id = id;
		this.idPassport = id_passport;
		this.idAuthor = id_organization;
		this.message = message_event;
		setDataTime(date_time_event);
		setType(type_event);
	}
	
	/**
	 * Конструктор используемый при создании события, перед помещением его в БД
	 * @param passport - паспорт, над которым совершается событие
	 * @param eventType - Тип события (Имя константы в перечислениях)
	 * @param dao - DAO создающий данное событие (нужен для считывания из БД автора события)
	 */
	public PassportEvent(Passport passport, Owner owner, String eventType){
		setType(TypeEvent.valueOf(eventType));
		setPassportandAothor(passport, owner);
	}
	
	/**
	 * Установить паспорт и автора события и все формирующиеся от них поля (с запросом к базе данных)
	 * @param passport - паспорт над которым совершается событие
	 */
	private void setPassportandAothor(Passport passport, Owner owner) {
		this.passport = passport;
		this.idPassport = passport.getID();
		this.idAuthor = passport.getIdOwner();
		this.auther = owner;
		this.nameAuthor = auther.getName();
		createMessage();
	}
	
	/**
	 * Установить id события
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * Установить сообщение события
	 */
	public void setMessage(String massage){
		this.message = massage;
	}
	
	/**
	 * Установить дату и время события
	 */
	public void setDataTime(DateTime dateTime){
		this.dateTime = dateTime;
	}
	
	/**
	 * Установить тип события
	 */
	public void setType(TypeEvent typeEvent){
		this.typeEvent = typeEvent;
	}
	
	/**
	 * Установить тип события
	 */
	public void setType(String typeEvent){
		this.typeEvent = TypeEvent.getTypeEvent(typeEvent);
	}
	
	/**
	 * Установить id автора события
	 */
	public void setIdAuthor(int idAuthor){
		this.idAuthor = idAuthor;
	}
	
	/**
	 * Установить id паспорт над которым было совершино событие
	 */
	public void setIdPassport(int idPassport){
		this.idPassport = idPassport;
	}
	
	/**
	 * Получить id события
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Получить сообщение события
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * Получить дату и время события
	 */
	public DateTime getDataTime(){
		return dateTime;
	}
	
	/**
	 * Пролучить время события
	 */
	public LocalTime getTime(){
		return dateTime.toLocalTime();
	}
	
	public LocalDate getDate(){
		return dateTime.toLocalDate();
	}
	
	/**
	 * Получить тип события
	 */
	public TypeEvent getTypeEvent(){
		return typeEvent;
	}
	
	/**
	 * Получить тип события (словами)
	 */
	public String getType(){
		return typeEvent.getType();
	}
	
	
	/**
	 * Получить id паспорт над которым было совершино событие
	 */
	public int getIdPassport(){
		return idPassport;
	}
	
	/**
	 * Нерекомендуемый способ получения пасспорта события - работает только, когда автор бы выбран из БД
	 * @return - автор события
	 */
	public Owner getAuther() {
		return auther;
	}

	/**
	 * Установить автора события
	 * @param auther - автор
	 */
	public void setAuther(Owner auther) {
		this.auther = auther;
	}
	
	/**
	 *  Нерекомендуемый способ получить паспорт для которого создано событие
	 * @return - паспорт
	 */
	public Passport getPassport() {
		return passport;
	}

	/**
	 * Получить id автора
	 * @return - id автора
	 */
	public int getIdAuthor() {
		return idAuthor;
	}
	
	/**
	 * Создание текстового сообщения события
	 */
	//TODO: Перенести в ДАО
	private void createMessage(){
		this.message = "Организация \"" +this.nameAuthor+ "\" (id = " + this.idAuthor + ") " +typeEvent.getWorldForMassege()+ " поле с id = " +this.idPassport;
	}
	

}
