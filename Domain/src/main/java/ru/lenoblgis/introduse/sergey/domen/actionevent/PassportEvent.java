package ru.lenoblgis.introduse.sergey.domen.actionevent;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;

public class PassportEvent implements Serializable{
	
	/**
	 * Id событи€
	 */
	private int  id;
	
	/**
	 * ƒата и врем€ событи€
	 */
	private DateTime dateTime;
	
	/**
	 * “ип событи€
	 */
	private TypeEvent typeEvent;	
	
	/**
	 * ID автора событи€
	 */
	private int idAuthor;
	
	
	/**
	 * јвтор событи€
	 */
	private Owner auther;
	
	/**
	 * ID паспорта, над которым было совершено событие
	 */
	private int idPassport;
	
	/**
	 * ѕаспорт, над которым было совершено событие
	 */
	private Passport passport;
	
	/**
	 * —ообщение данного сообщени€
	 */
	private String message;
	
	/**
	 *  онструктор по-умолчанию
	 */
	public PassportEvent(){
		this.typeEvent = TypeEvent.UNKNOWN;
	}
	
	/**
	 *  онструктор дл€ отображени€ записей в программное представление
	 * @param id - id событи€
	 * @param id_passport - id паспорта над которым было совершено событие
	 * @param id_organization - id организации выполнившей действи€ по данному событию
	 * @param message_event - текстовое сообщение событи€
	 * @param date_time_event - ƒата и врем€ событи€
	 * @param type_event - тип событи€
	 */
	public PassportEvent(int id, int id_passport, int id_organization, String message, DateTime date_time_event,  String type_event){
		this.id = id;
		this.idPassport = id_passport;
		this.idAuthor = id_organization;
		this.message = message;
		setDataTime(date_time_event);
		setType(type_event);
	}
	
	/**
	 *  онструктор используемый при создании событи€, перед помещением его в Ѕƒ
	 * @param passport - паспорт, над которым совершаетс€ событие
	 * @param eventType - “ип событи€ (»м€ константы в перечислени€х)
	 * @param dao - DAO создающий данное событие (нужен дл€ считывани€ из Ѕƒ автора событи€)
	 */
	public PassportEvent(Passport passport, Owner owner, String eventType){
		setType(TypeEvent.valueOf(eventType));
		this.passport = passport;
		this.idPassport = passport.getId();
		this.idAuthor = owner.getId();
		this.auther = owner;
	}
	
	/**
	 * ”становить id событи€
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * ”становить дату и врем€ событи€
	 */
	public void setDataTime(DateTime dateTime){
		this.dateTime = dateTime;
	}
	
	/**
	 * ”становить тип событи€
	 */
	public void setType(TypeEvent typeEvent){
		this.typeEvent = typeEvent;
	}
	
	/**
	 * ”становить тип событи€
	 */
	public void setType(String typeEvent){
		this.typeEvent = TypeEvent.getTypeEvent(typeEvent);
	}
	
	/**
	 * ”становить id автора событи€
	 */
	public void setIdAuthor(int idAuthor){
		this.idAuthor = idAuthor;
	}
	
	/**
	 * ”становить id паспорт над которым было совершино событие
	 */
	public void setIdPassport(int idPassport){
		this.idPassport = idPassport;
	}
	
	/**
	 * ѕолучить id событи€
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * ѕолучить дату и врем€ событи€
	 */
	public DateTime getDataTime(){
		return dateTime;
	}
	
	/**
	 * ѕролучить врем€ событи€
	 */
	public LocalTime getTime(){
		return dateTime.toLocalTime();
	}
	
	public LocalDate getDate(){
		return dateTime.toLocalDate();
	}
	
	/**
	 * ѕолучить тип событи€
	 */
	public TypeEvent getTypeEvent(){
		return typeEvent;
	}
	
	/**
	 * ѕолучить тип событи€ (словами)
	 */
	public String getType(){
		return typeEvent.getType();
	}
	
	
	/**
	 * ѕолучить id паспорт над которым было совершино событие
	 */
	public int getIdPassport(){
		return idPassport;
	}
	
	/**
	 * Ќерекомендуемый способ получени€ пасспорта событи€ - работает только, когда автор бы выбран из Ѕƒ
	 * @return - автор событи€
	 */
	public Owner getAuther() {
		return auther;
	}

	/**
	 * ”становить автора событи€
	 * @param auther - автор
	 */
	public void setAuther(Owner auther) {
		this.auther = auther;
	}
	
	/**
	 *  Ќерекомендуемый способ получить паспорт дл€ которого создано событие
	 * @return - паспорт
	 */
	public Passport getPassport() {
		return passport;
	}

	/**
	 * ѕолучить id автора
	 * @return - id автора
	 */
	public int getIdAuthor() {
		return idAuthor;
	}

	/**
	 * ѕолучить текст сообщени€ о событии
	 * @return - текст сообщени€ о событии
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * ”становить текст сообщени€ о событии
	 * @param message - новый текст сообщени€ о событии
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idAuthor;
		result = prime * result + idPassport;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassportEvent other = (PassportEvent) obj;
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
		sb.append(", typeEvent=").append(getType());
		sb.append(", idAuthor=").append(getIdAuthor());
		sb.append(", idPassport=").append(getIdPassport());
		sb.append('}');
		return sb.toString();
	}
}
