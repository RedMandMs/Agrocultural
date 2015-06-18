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
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.domen.actionevent.PassportEvent;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;

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

	public List<EventInfo> findEvents(EventInfo serchingEvent) {
	
		List<EventInfo> findInfo = new ArrayList<EventInfo>();
		
		PassportEvent findingEvent = convertDTOToDomain(serchingEvent);

		String nameAuthor = serchingEvent.getNameAuthor();
		if(nameAuthor != null && !nameAuthor.trim().equals("")){
			Organization organization = dao.findOwnerByName(nameAuthor);
			
			//���� ��� id (���� ��� �� ����� null) ��������� ��� ������ �� ������������� id, ������� ����������� � ���������������� ���������� ����� - ����� ����������� �� �����
			if(serchingEvent.getIdAuthor() != organization.getId() && serchingEvent.getIdAuthor() != null){
				return findInfo;
			}
			//���� �� id ������� �� ����, �� ���� ������� ���, �� ����������� id � �������
			if(serchingEvent.getId() == null){
				findingEvent.setIdAuthor(organization.getId());
			}
		}
		
		List<PassportEvent> findEvents = dao.findEvents(findingEvent);
		
		for(PassportEvent event : findEvents){
			findInfo.add(convertDomainToDTO(event));
		}
		
		return findInfo;
	}

	private EventInfo convertDomainToDTO(PassportEvent event) {
		return new EventInfo(event.getId(), event.getIdPassport(), event.getIdAuthor(), event.getMessage(), event.getDataTime(), event.getType());
	}

	private PassportEvent convertDTOToDomain(EventInfo event) {
		return new PassportEvent(event.getId(), event.getIdPassport(), event.getIdAuthor(), event.getMessage(), event.getDataTime(), event.getTypeEvent());
	}

	public void deleteEvent(Integer idEvent) {
		dao.deletePassportEvent(idEvent);
	}

}
