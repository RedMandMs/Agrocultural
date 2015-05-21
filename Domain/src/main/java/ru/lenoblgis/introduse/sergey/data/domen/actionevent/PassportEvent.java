package ru.lenoblgis.introduse.sergey.data.domen.actionevent;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;

public class PassportEvent implements Serializable{
	
	/**
	 * Id �������
	 */
	private int  id;
	
	/**
	 * ��������� ��������� �������
	 */
	private String message;
	
	/**
	 * ���� � ����� �������
	 */
	private DateTime dateTime;
	
	/**
	 * ��� �������
	 */
	private TypeEvent typeEvent;	
	
	/**
	 * ID ������ �������
	 */
	private int idAuthor;
	
	/**
	 * ��� ������ ������� (�������� �����������)
	 */
	private String nameAuthor;
	
	/**
	 * ����� �������
	 */
	private Owner auther;
	
	/**
	 * ID ��������, ��� ������� ���� ��������� �������
	 */
	private int idPassport;
	
	/**
	 * �������, ��� ������� ���� ��������� �������
	 */
	private Passport passport;
	
	/**
	 * ����������� ��-���������
	 */
	public PassportEvent(){
		this.typeEvent = TypeEvent.UNKNOWN;
		createMessage();
	}
	
	/**
	 * ����������� ��� ����������� ������� � ����������� �������������
	 * @param id - id �������
	 * @param id_passport - id �������� ��� ������� ���� ��������� �������
	 * @param id_organization - id ����������� ����������� �������� �� ������� �������
	 * @param message_event - ��������� ��������� �������
	 * @param date_time_event - ���� � ����� �������
	 * @param type_event - ��� �������
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
	 * ����������� ������������ ��� �������� �������, ����� ���������� ��� � ��
	 * @param passport - �������, ��� ������� ����������� �������
	 * @param eventType - ��� ������� (��� ��������� � �������������)
	 * @param dao - DAO ��������� ������ ������� (����� ��� ���������� �� �� ������ �������)
	 */
	public PassportEvent(Passport passport, Owner owner, String eventType){
		setType(TypeEvent.valueOf(eventType));
		setPassportandAothor(passport, owner);
	}
	
	/**
	 * ���������� ������� � ������ ������� � ��� ������������� �� ��� ���� (� �������� � ���� ������)
	 * @param passport - ������� ��� ������� ����������� �������
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
	 * ���������� id �������
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * ���������� ��������� �������
	 */
	public void setMessage(String massage){
		this.message = massage;
	}
	
	/**
	 * ���������� ���� � ����� �������
	 */
	public void setDataTime(DateTime dateTime){
		this.dateTime = dateTime;
	}
	
	/**
	 * ���������� ��� �������
	 */
	public void setType(TypeEvent typeEvent){
		this.typeEvent = typeEvent;
	}
	
	/**
	 * ���������� ��� �������
	 */
	public void setType(String typeEvent){
		this.typeEvent = TypeEvent.getTypeEvent(typeEvent);
	}
	
	/**
	 * ���������� id ������ �������
	 */
	public void setIdAuthor(int idAuthor){
		this.idAuthor = idAuthor;
	}
	
	/**
	 * ���������� id ������� ��� ������� ���� ��������� �������
	 */
	public void setIdPassport(int idPassport){
		this.idPassport = idPassport;
	}
	
	/**
	 * �������� id �������
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * �������� ��������� �������
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * �������� ���� � ����� �������
	 */
	public DateTime getDataTime(){
		return dateTime;
	}
	
	/**
	 * ��������� ����� �������
	 */
	public LocalTime getTime(){
		return dateTime.toLocalTime();
	}
	
	public LocalDate getDate(){
		return dateTime.toLocalDate();
	}
	
	/**
	 * �������� ��� �������
	 */
	public TypeEvent getTypeEvent(){
		return typeEvent;
	}
	
	/**
	 * �������� ��� ������� (�������)
	 */
	public String getType(){
		return typeEvent.getType();
	}
	
	
	/**
	 * �������� id ������� ��� ������� ���� ��������� �������
	 */
	public int getIdPassport(){
		return idPassport;
	}
	
	/**
	 * ��������������� ������ ��������� ��������� ������� - �������� ������, ����� ����� �� ������ �� ��
	 * @return - ����� �������
	 */
	public Owner getAuther() {
		return auther;
	}

	/**
	 * ���������� ������ �������
	 * @param auther - �����
	 */
	public void setAuther(Owner auther) {
		this.auther = auther;
	}
	
	/**
	 *  ��������������� ������ �������� ������� ��� �������� ������� �������
	 * @return - �������
	 */
	public Passport getPassport() {
		return passport;
	}

	/**
	 * �������� id ������
	 * @return - id ������
	 */
	public int getIdAuthor() {
		return idAuthor;
	}
	
	/**
	 * �������� ���������� ��������� �������
	 */
	//TODO: ��������� � ���
	private void createMessage(){
		this.message = "����������� \"" +this.nameAuthor+ "\" (id = " + this.idAuthor + ") " +typeEvent.getWorldForMassege()+ " ���� � id = " +this.idPassport;
	}
	

}
