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
	 * ��������� ��������� ������������ ���� ���� �� ��������
	 * @param title - �������� ���� ����
	 * @return - ��������� ������� ������������
	 */
	public static TypeField getType(String title){
		TypeField[] values = TypeField.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].type)) return values[i];
		}
		return null;
	}

	/**
	 * ��� ���� (�������)
	 */
	private String type;

	/**
	 * �������� ��� ����
	 * @return - ��� ���� (�������)
	 */
	public String getType() {
		return type;
	}

}
