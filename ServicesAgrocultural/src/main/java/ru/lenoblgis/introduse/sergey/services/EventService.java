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
	 * DAO для работы с базой данных
	 */
	private DAO dao = new DAO();
	
	/**
	 * Получить все события, которые совершались какими-либо владелецами
	 * @return - Список информации о событиях ("id" - id события, 
	 * 										   "id_passport" - id пасспорта над которым совершалось действие,
	 * 										   "id_organization" - id организации совершившей действие
	 * 										   "message" - сообщение события
	 * 										   "date_time_event" - дата и время события
	 * 										   "type_event" - тип события)
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
	 * Получить все события, которые совершал переданный владелец
	 * @param idOwner - id влдельца
	 * @return - Список информации о событиях ("id" - id события, 
	 * 										   "id_passport" - id пасспорта над которым совершалось действие,
	 * 										   "id_organization" - id организации совершившей действие
	 * 										   "message" - сообщение события
	 * 										   "date_time_event" - дата и время события
	 * 										   "type_event" - тип события)
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
