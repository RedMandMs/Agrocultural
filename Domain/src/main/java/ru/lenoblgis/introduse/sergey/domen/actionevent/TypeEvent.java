package ru.lenoblgis.introduse.sergey.domen.actionevent;

import java.io.Serializable;

public enum TypeEvent implements Serializable{
	
	UNKNOWN("Неизвестно", "Неизвестно что сделало"),
	ADDITION("Добавление поля", "Добавила"),
	DELETION("Удаление поля", "Удалила"),
	EDITION("Редактирование поля", "Отредактировала"),
	REVIEW("Просмотр поля", "Просмотрела");
	
	TypeEvent(String type, String worldForMassege){
		this.type = type;
		this.worldForMassege = worldForMassege;
	}
	
	/**
	 * Тип события
	 */
	private String type;

	
	/**
	 * Слово для добавление в текст события
	 */
	private String worldForMassege;
	
	/**
	 * Получить тип события
	 * @return - тип события
	 */
	public String getType() {
		return type;
	}

	/**
	 * Получения слова, используемого в текстовом сообщении о событии
	 * @return - слово для текстового сообщения
	 */
	public String getWorldForMassege() {
		return worldForMassege;
	}
	
	/**
	 * Получение константы перечисления типа по названию
	 * @param title - как записано в БД
	 * @return - константа перечисления, соответствующая названию типа
	 */
	public static TypeEvent getTypeEvent(String title){
		TypeEvent[] values = TypeEvent.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].type)) return values[i];
		}
		return null;
	}
	
	

}
