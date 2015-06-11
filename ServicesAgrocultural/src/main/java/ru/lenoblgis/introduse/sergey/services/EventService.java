package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.datatransferobject.event.EventInfo;
import ru.lenoblgis.introduse.sergey.domen.actionevent.PassportEvent;

@Service("eventService")
public class EventService implements Serializable{

	/**
	 * DAO ��� ������ � ����� ������
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * �������� ��� �������, ������� ����������� ������-���� �����������
	 * @return - ������ ���������� � �������� ("id" - id �������, 
	 * 										   "id_passport" - id ��������� ��� ������� ����������� ��������,
	 * 										   "id_organization" - id ����������� ����������� ��������
	 * 										   "message" - ��������� �������
	 * 										   "date_time_event" - ���� � ����� �������
	 * 										   "type_event" - ��� �������)
	 */
	public List<EventInfo> getAllEvents(){
		List<EventInfo> listEvents = new ArrayList<EventInfo>();
		
		List<PassportEvent> events = dao.reviewAllPassportEvent();
		for(PassportEvent event : events){
			EventInfo eventInfo = new EventInfo(event.getId(), event.getIdPassport(), event.getIdAuthor(), event.getMessage(), event.getDataTime(), event.getType());
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
	public List<EventInfo> getAllOwnerEvents(int idOwner){
		List<EventInfo> listEvents = new ArrayList<EventInfo>();
		
		List<PassportEvent> events = dao.reviewAllOwnerEvents(idOwner);
		
		for(PassportEvent event : events){
			EventInfo eventInfo = new EventInfo(event.getId(), event.getIdPassport(), idOwner, event.getMessage(), event.getDataTime(), event.getType());
			listEvents.add(eventInfo);
		}
		
		return listEvents;
	}

}
