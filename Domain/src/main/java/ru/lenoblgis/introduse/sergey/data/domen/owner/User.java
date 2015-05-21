package ru.lenoblgis.introduse.sergey.data.domen.owner;

public class User {
	
	/**
	 * id ������������
	 */
	private int id;
	
	/**
	 * ����� ������������
	 */
	private String userLogin;

	/**
	 * ������ ������������
	 */
	private String userPassword;
	
	/**
	 * id �����������, ������� ������� ������������
	 */
	private int organizationId;
	
		
	
	/**
	 * ����������� ��-���������
	 */
	public User() {
	}
	
	/**
	 * ����������� ������������ ��������� ������ ������������ � ��
	 * @param id - id ������������
	 * @param userLogin - ����� ������������
	 * @param userPassword - ������ ������������
	 * @param organizationId - id �����������, ����������, ������� ��-�� ������������
	 */
	public User(String userLogin, String userPassword, int organizationId) {
		super();
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.organizationId = organizationId;
	}

	/**
	 * ����������� ������������ ��� ������� ���� �� ��
	 * @param id - id ������������
	 * @param userLogin - ����� ������������
	 * @param userPassword - ������ ������������
	 * @param organizationId - id �����������, ����������, ������� ��-�� ������������
	 */
	public User(int id, String userLogin, String userPassword,
			int organizationId) {
		super();
		this.id = id;
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.organizationId = organizationId;
	}


	/**
	 * �������� id ������������
	 * @return - id ������������
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * ���������� id ������������
	 * @param id - ����� id ������������
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * �������� ����� ������������
	 * @return - ����� ������������
	 */
	public String getUserLogin() {
		return userLogin;
	}
	
	/**
	 * ���������� ����� ������������
	 * @param userLogin - ����� ����� ������������
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	/**
	 * �������� ������ ������������
	 * @return - ������ ������������
	 */
	public String getUserPassword() {
		return userPassword;
	}
	
	/**
	 * ���������� ����� ������ ������������
	 * @param userPassword - ������ ������������
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	/**
	 * �������� id �����������, ������� ������� ������������
	 * @return - id ����������� �����������
	 */
	public int getOrganizationId() {
		return organizationId;
	}
	
	/**
	 * ���������� id �����������, ������� ����� ������� ������������
	 * @param organizationId - ����� id �����������
	 */
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

}
