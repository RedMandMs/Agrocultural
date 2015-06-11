package ru.lenoblgis.introduse.sergey.domen.passport;

import java.io.Serializable;

/**
 * Перечисление регионов
 * @author Vilgodskiy
 *
 */
public enum RegionField implements Serializable{
	
	NULL(null, "Не указано"),
	UNKNOWN("Neizv", "Неизвестно"),
	VSEVOLOGSK("vsevologskiy", "Всеволожский район"),
	PRIZEMSK("Prizemskiy", "Приозерский район");
	
	
	private RegionField(String region, String view){
		this.region = region;
		this.view = view;
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
	 * Отображаемое имя
	 */
	private String view;
	
	/**
	 * Получить название региона
	 * @return - название региона
	 */
	public String getRegion() {
		return region;
	}

	public String getView() {
		return view;
	}

}
