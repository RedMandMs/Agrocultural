package ru.lenoblgis.introduse.sergey.domen.actionevent;

import java.io.Serializable;

public enum TypeEvent implements Serializable{
	
	UNKNOWN("����������", "���������� ��� �������"),
	ADDITION("���������� ����", "��������"),
	DELETION("�������� ����", "�������"),
	EDITION("�������������� ����", "���������������"),
	REVIEW("�������� ����", "�����������");
	
	TypeEvent(String type, String worldForMassege){
		this.type = type;
		this.worldForMassege = worldForMassege;
	}
	
	/**
	 * ��� �������
	 */
	private String type;

	
	/**
	 * ����� ��� ���������� � ����� �������
	 */
	private String worldForMassege;
	
	/**
	 * �������� ��� �������
	 * @return - ��� �������
	 */
	public String getType() {
		return type;
	}

	/**
	 * ��������� �����, ������������� � ��������� ��������� � �������
	 * @return - ����� ��� ���������� ���������
	 */
	public String getWorldForMassege() {
		return worldForMassege;
	}
	
	/**
	 * ��������� ��������� ������������ ���� �� ��������
	 * @param title - ��� �������� � ��
	 * @return - ��������� ������������, ��������������� �������� ����
	 */
	public static TypeEvent getTypeEvent(String title){
		TypeEvent[] values = TypeEvent.values();
		for (int i = 0; i < values.length; i++) {
			if(title.equals(values[i].type)) return values[i];
		}
		return null;
	}
	
	

}
