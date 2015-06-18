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
	 * DAO для работы с базой данных
	 */
	@Autowired
	private DAO dao;

	/**
	 * Получить все события, которые совершал переданный владелец
	 * @param idOwner - id влдельца
	 * @return - Список информации о событиях ("id" - id события, 
	 * 										   "id_passport" - id пасспорта над которым совершалось действие,
	 * 										   "id_organization" - id организации совершившей действие
	 * 										   "message" - сообщение события
	 * 										   "date_time_event" - дата и время события
	 * 										   "type_event" - тип события)
	 */
	public List<EventInfo> getAllOwnerEvents(int idOwner){
		List<EventInfo> listEvents = new ArrayList<EventInfo>();
		
		PassportEvent passportEvent = new PassportEvent();
		passportEvent.setIdAuthor(idOwner);
		
		List<PassportEvent> events = dao.findEvents(passportEvent);
		
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
			Organization organization = new Organization();
			organization.setName(nameAuthor);
			organization = dao.findOwners(organization).get(0);
			
			//Если имя id (если оно не равно null) указанное при поиске не соответствует id, которое прикреплено к соответствующему указанному имени - тогда результатов не будет
			if(serchingEvent.getIdAuthor() != organization.getId() && serchingEvent.getIdAuthor() != null){
				return findInfo;
			}
			//Если же id указано не было, но было указано имя, то прикрепляем id к запросу
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
