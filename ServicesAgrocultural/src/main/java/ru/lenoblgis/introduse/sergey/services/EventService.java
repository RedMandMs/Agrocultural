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
	 * DAO для работы с базой данных
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * Получить все события, которые совершались какими-либо владелецами
	 * @return - Список информации о событиях ("id" - id события, 
	 * 										   "id_passport" - id пасспорта над которым совершалось действие,
	 * 										   "id_organization" - id организации совершившей действие
	 * 										   "message" - сообщение события
	 * 										   "date_time_event" - дата и время события
	 * 										   "type_event" - тип события)
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
		
		List<PassportEvent> events = dao.reviewAllOwnerEvents(idOwner);
		
		for(PassportEvent event : events){
			EventInfo eventInfo = new EventInfo(event.getId(), event.getIdPassport(), idOwner, event.getMessage(), event.getDataTime(), event.getType());
			listEvents.add(eventInfo);
		}
		
		return listEvents;
	}

}
