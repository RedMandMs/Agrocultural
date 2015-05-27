package ru.lenoblgis.introduse.sergey.data.dao.sqlQueries;

import java.io.Serializable;
import java.util.Map;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.user.User;

public interface SQLQueries extends Serializable {

	/**
	 * �������� ���������
	 * @param owner 
	 */
	public String createOwner(Owner owner);
	
	/**
	 * �������� ���������
	 */
	public String deleteOwner();
	
	/**
	 * ������������� ���������
	 */
	public String editOwner();
	
	/**
	 * ����������� ���������
	 */
	public String reviewOwner();
	
	
	/**
	 * �������� ��������
	 */
	public String createPassport();
	
	/**
	 * �������� ��������
	 */
	public String deletePassport();
	
	/**
	 * �������������� ��������
	 */
	public String editPassport();
	
	/**
	 * �������� ��������
	*/
	public String reviewPassport();
	
	/**
	 * �������� ���� ����������
	 */
	public String reviewAllPassports();
	
	/**
	 * ����� ��������
	 */
	public String findPassports(Map<String,String> info);
	
	/**
	 * ������������ ������ ��� ������� �������
	 * @return - ������ ������� ������� ���������
	 */
	public String createPassportEvent();
	
	/**
	 * ������������ ������ ��� �������� �������
	 * @return - ������ �������� ������� ���������
	 */
	public String deletePassportEvent();
	
	/**
	 * ������������ ������ ��� ������� ���� ������� �������
	 * @return - ������ �� ������� ������� ����������
	 */
	public String reviewAllPassportEvent();
	
	/**
	 * ������������ ������ ��� ������� �������, �������������� ��������� �������������
	 * @return - ������ �� ������� ������� ���������� ���������
	 */
	public String reviwAllOwnerPassportEvent();
	
	/**
	 * ������������ ������ ��� ��������� ������������� id �������� ���������� ������������ � ���������� id
	 * @return - ������
	 */
	public String getMAXidPassportByOwner();
	
	/**
	 * ������������ ������ ��� �������� ���������� ������ � ������ ������������ (�����������)
	 * @return - ������
	 */
	public String authorization();
	
	/**
	 * ������������ ������ ��� �������� ������ ������������ ������� (�����������)
	 * @return - ������
	 */
	public String createUser(User user);
}
