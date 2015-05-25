package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.actionevent.PassportEvent;

@Service("eventService")
public class EventService implements Serializable{

	/**
	 * DAO ��� ������ � ����� ������
	 */
	private DAO dao = new DAO();
	
	/**
	 * �������� ��� �������, ������� ����������� ������-���� �����������
	 * @return - ������ ���������� � �������� ("id" - id �������, 
	 * 										   "id_passport" - id ��������� ��� ������� ����������� ��������,
	 * 										   "id_organization" - id ����������� ����������� ��������
	 * 										   "message" - ��������� �������
	 * 										   "date_time_event" - ���� � ����� �������
	 * 										   "type_event" - ��� �������)
	 */
	public List<Map<String, String>> getAllEvents(){
		List<Map<String, String>> listEvents = new ArrayList<Map<String,String>>();
		
		List<PassportEvent> events = dao.reviewAllPassportEvent();
		for(PassportEvent event : events){
			Map<String, String> eventInfo = new HashMap<String, String>();
			eventInfo.put("id", String.valueOf(event.getId()));
			eventInfo.put("id_passport", String.valueOf(event.getIdPassport()));
			eventInfo.put("id_organization", String.valueOf(event.getIdAuthor()));
			eventInfo.put("message", event.getMessage());
			eventInfo.put("date_time_event", event.getDate() + "  " + event.getTime().toString());
			eventInfo.put("type_event", event.getType());
			listEvents.add(eventInfo);
		}
		
		return listEvents;
	}

	/**
	 * �������� ��� �������, ������� �������� ���������� ��������
	 * @param idOwner - id ��������
	 * @return - ������ ���������� � �������� ("id" - id �������, 
	 * 										   "id_passport" - id ��������� ��� ������� ����������� ��������,
	 * 										   "id_organization" - id ����������� ����������� ��������
	 * 										   "message" - ��������� �������
	 * 										   "date_time_event" - ���� � ����� �������
	 * 										   "type_event" - ��� �������)
	 */
	public List<Map<String, String>> getAllOwnerEvents(int idOwner){
		List<Map<String, String>> listEvents = new ArrayList<Map<String,String>>();
		
		List<PassportEvent> events = dao.reviewAllOwnerEvents(idOwner);
		for(PassportEvent event : events){
			Map<String, String> eventInfo = new HashMap<String, String>();
			eventInfo.put("id", String.valueOf(event.getId()));
			eventInfo.put("id_passport", String.valueOf(event.getIdPassport()));
			eventInfo.put("id_organization", String.valueOf(event.getIdAuthor()));
			eventInfo.put("message", event.getMessage());
			eventInfo.put("date_time_event", event.getDate() + "  " + event.getTime().toString());
			eventInfo.put("type_event", event.getType());
			listEvents.add(eventInfo);
		}
		
		return listEvents;
	}

}
