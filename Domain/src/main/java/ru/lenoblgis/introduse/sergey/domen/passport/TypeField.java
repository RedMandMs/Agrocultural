package ru.lenoblgis.introduse.sergey.domen.passport;

import java.io.Serializable;

public enum TypeField implements Serializable{
	
	NULL(null, " "),
	UNKNOWN("Unkown", "Неизвестно"),
	FARM("Ferm", "Фермерское хозяйство"),
	AGROCULTURAL("Selo", "Сельско-хозяйственнон производство"),
	COLLECTIVE_FARM("Krest", "Крестьянское хозяйство"),
	COLLECTIVE_AGROCULTURAL("KrestAgrolut", "Для организации крестьянского хозяйства");
	
	private TypeField(String type, String view) {
		this.type = type;
		this.view = view;
	}

	/**
	 * Получение константы перечисления типа поля по названию
	 * @param title - название типа поля
	 * @return - константа данного перечисления
	 */
	public static TypeField getTypeOf(String title){
		TypeField[] values = TypeField.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].type)) return values[i];
		}
		return null;
	}

	/**
	 * Тип поля (словами)
	 */
	private String type;

	/**
	 * Тип поля для отображения
	 */
	private String view; 
	
	/**
	 * Получить тип поля
	 * @return - тип поля (словами)
	 */
	public String getType() {
		return type;
	}

	public String getView() {
		return view;
	}

	
}
