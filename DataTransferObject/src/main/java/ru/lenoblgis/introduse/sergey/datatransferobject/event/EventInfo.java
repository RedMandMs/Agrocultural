package ru.lenoblgis.introduse.sergey.datatransferobject.event;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class EventInfo implements Serializable{

	/**
	 * Id события
	 */
	private int  id;
	
	/**
	 * Дата и время события
	 */
	private DateTime dateTime;
	
	/**
	 * Тип события
	 */
	private String typeEvent;	
	
	/**
	 * ID автора события
	 */
	private int idAuthor;
	
	
	/**
	 * Автор события
	 */
	private String nameAuther;
	
	/**
	 * ID паспорта, над которым было совершено событие
	 */
	private int idPassport;
	
	/**
	 * Сообщение данного сообщения
	 */
	private String message;
	
	/**
	 * Конструктор по-умолчанию
	 */
	public EventInfo(){
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
	public EventInfo(int id, int id_passport, int id_organization, String message, DateTime date_time_event,  String type_event){
		this.id = id;
		this.idPassport = id_passport;
		this.idAuthor = id_organization;
		setDataTime(date_time_event);
		setTypeEvent(type_event);
	}
	
	/**
	 * Конструктор используемый при создании события, перед помещением его в БД
	 * @param passport - паспорт, над которым совершается событие
	 * @param eventType - Тип события (Имя константы в перечислениях)
	 * @param dao - DAO создающий данное событие (нужен для считывания из БД автора события)
	 */
	public EventInfo(int idPassport, int idAuthor, String nameAuther, String eventType){
		setTypeEvent(eventType);
		this.idPassport = idPassport;
		this.idAuthor = idAuthor;
		this.nameAuther= nameAuther;
	}
	
	/**
	 * Установить id события
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * Установить дату и время события
	 */
	public void setDataTime(DateTime dateTime){
		this.dateTime = dateTime;
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
	public String getTypeEvent(){
		return typeEvent;
	}
	
	/**
	 * Получить id паспорт над которым было совершино событие
	 */
	public int getIdPassport(){
		return idPassport;
	}

	/**
	 * Получить id автора
	 * @return - id автора
	 */
	public int getIdAuthor() {
		return idAuthor;
	}

	/**
	 * Получить текст сообщения о событии
	 * @return - текст сообщения о событии
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Установить текст сообщения о событии
	 * @param message - новый текст сообщения о событии
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Получить дату события
	 * @return - получить дату события
	 */
	public DateTime getDateTime() {
		return dateTime;
	}

	/**
	 * Установить дату события
	 * @param dateTime - дата события
	 */
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Получить имя владельца
	 * @return - имя владельца
	 */
	public String getNameAuther() {
		return nameAuther;
	}

	/**
	 * Установить новое имя владельца
	 * @param nameAuther - новое имя владельца
	 */
	public void setNameAuther(String nameAuther) {
		this.nameAuther = nameAuther;
	}

	/**
	 * Установить тип события
	 * @param typeEvent - новый тип события
	 */
	public void setTypeEvent(String typeEvent) {
		this.typeEvent = typeEvent;
	}
	
	
}
