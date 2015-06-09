package ru.lenoblgis.introduse.sergey.domen.passport;

import java.io.Serializable;

public enum TypeField implements Serializable{
	
	FARM("Ferm"),
	AGROCULTURAL("Selo"),
	COLLECTIVE_FARM("Krest");
	
	private TypeField(String type) {
		this.type = type;
	}
	
	/**
	 * ѕолучение константы перечислени€ типа пол€ по названию
	 * @param title - название типа пол€
	 * @return - константа данного перечислени€
	 */
	public static TypeField getType(String title){
		TypeField[] values = TypeField.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].type)) return values[i];
		}
		return null;
	}

	/**
	 * “ип пол€ (словами)
	 */
	private String type;

	/**
	 * ѕолучить тип пол€
	 * @return - тип пол€ (словами)
	 */
	public String getType() {
		return type;
	}

}
