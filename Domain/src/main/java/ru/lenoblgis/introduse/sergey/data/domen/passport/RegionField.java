package ru.lenoblgis.introduse.sergey.data.domen.passport;

import java.io.Serializable;

/**
 * Перечисление регионов
 * @author Vilgodskiy
 *
 */
public enum RegionField implements Serializable{
	
	UNKNOWN("Неизвестный"),
	VSEVOLOGSK("Всеволожский р-н"),
	PRIZEMSK("Приземский р-н");
	
	private RegionField(String region){
		this.region = region;
	}
	
	/**
	 * Получение константы перечисления региона по названию
	 * @param title - как записано в БД
	 * @return - константа перечисления, соответствующая названию региона
	 */
	public static RegionField getRegion(String title){
		RegionField[] values = RegionField.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].region)) return values[i];
		}
		return null;
	}

	/**
	 * Имя региона
	 */
	private String region;

	/**
	 * Получить название региона
	 * @return - название региона
	 */
	public String getRegion() {
		return region;
	}

}
