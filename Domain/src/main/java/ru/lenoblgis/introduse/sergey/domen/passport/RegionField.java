package ru.lenoblgis.introduse.sergey.domen.passport;

import java.io.Serializable;

/**
 * ������������ ��������
 * @author Vilgodskiy
 *
 */
public enum RegionField implements Serializable{
	
	UNKNOWN("Neizv"),
	VSEVOLOGSK("vsevologskiy"),
	PRIZEMSK("Prizemskiy");
	
	private RegionField(String region){
		this.region = region;
	}
	
	/**
	 * ��������� ��������� ������������ ������� �� ��������
	 * @param title - ��� �������� � ��
	 * @return - ��������� ������������, ��������������� �������� �������
	 */
	public static RegionField getRegion(String title){
		RegionField[] values = RegionField.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].region)) return values[i];
		}
		return null;
	}

	/**
	 * ��� �������
	 */
	private String region;

	/**
	 * �������� �������� �������
	 * @return - �������� �������
	 */
	public String getRegion() {
		return region;
	}

}
