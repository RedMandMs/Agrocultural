package ru.lenoblgis.introduse.sergey.data.domen.passport;

import java.io.Serializable;

public enum TypeField implements Serializable{
	
	FARM("Фермерское хозяйство"),
	AGROCULTURAL("Сельскохозяйственное производство"),
	COLLECTIVE_FARM("Крестьянское хозяйство");
	
	private TypeField(String type) {
		this.type = type;
	}
	
	/**
	 * Получение константы перечисления типа поля по названию
	 * @param title - название типа поля
	 * @return - константа данного перечисления
	 */
	public static TypeField getType(String title){
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
	 * Получить тип поля
	 * @return - тип поля (словами)
	 */
	public String getType() {
		return type;
	}

}
