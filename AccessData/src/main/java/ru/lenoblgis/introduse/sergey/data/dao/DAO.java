package ru.lenoblgis.introduse.sergey.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import ru.lenoblgis.introduse.sergey.data.dao.mappers.EventRowMapper;
import ru.lenoblgis.introduse.sergey.data.dao.mappers.OrganizationRowMapper;
import ru.lenoblgis.introduse.sergey.data.dao.mappers.PassportRowMapper;
import ru.lenoblgis.introduse.sergey.data.dao.mappers.UserRowMapper;
import ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries;
import ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLServerQueries;
import ru.lenoblgis.introduse.sergey.data.domen.actionevent.PassportEvent;
import ru.lenoblgis.introduse.sergey.data.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.data.domen.owner.User;
import ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DAO  {
	
	/**
	 * �\�������� ����� ������������ ��� ���������� ������� - ���������� ����
	 */
	private static final String ADD_EVENT = "ADDITION";
	
	/**
	 * ��������� ����� ������������ ��� ���������� ������� - �������������e ����
	 */
	private static final String EDIT_EVENT = "EDITION";
	
	/**
	 * ��������� ����� ������������ ��� ���������� ������� - �������� ����
	 */
	private static final String DELETE_EVENT = "DELETION";
	
	/**
	 * ��������� ����� ������������ ��� ���������� ������� - �������� ����
	 */
	private static final String REVIEW_EVENT = "REVIEW";

	/**
	 * ����������� ��-���������
	 */
	public DAO(){
		postConstruct();
	}
	
	/**
	 * ������ ��� ��������� ������ ��������
	 */
	SQLQueries sqlQueries = new SQLServerQueries();
	/**
	 * ������ DataSource
	 */
	SQLServerDataSource ds = null;
	/**
	 * ������ ������ ��� �������������� ���� ������
	 */
	JdbcTemplate jdbcTemplate = null;
	/**
	 * ������, ������������ �������� � ����������� ������ �� ��
	 */
	PassportRowMapper passportRowMapper = new PassportRowMapper();
	/**
	 * ������, ������������ ����������� � ����������� ������ �� ��
	 */
	OrganizationRowMapper organizationRowMapper = new OrganizationRowMapper();
	/**
	 * ������, ������������ ������� � ����������� ������ �� ��
	 */
	EventRowMapper eventRowMapper = new EventRowMapper();
	/**
	 * ������, ������������ ������������ � ����������� ������ �� ��
	 */
	UserRowMapper userRowMapper = new UserRowMapper();
	
	/**
	 * ����������� DataSource � ���� ������ � �������� jdbcTemplate
	 */
	public void postConstruct(){
		ds = new SQLServerDataSource();
		ds.setPortNumber(1433);
		ds.setHostNameInCertificate("localhost");
		ds.setDatabaseName("passport_agricultural");
		ds.setUser("adminAgricultural");
		ds.setPassword("admin123");
		
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#createOwner(java.util.Map)
	 */
	public void createOwner(Owner owner) {
		Object [] values = new Object[]{owner.getName(), owner.getINN(), owner.getAddres()};
		String sqlQuery = sqlQueries.createOwner();
		jdbcTemplate.update(sqlQuery, values);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#deleteOwner(java.util.Map)
	 */
	public void deleteOwner(int idOwner) {
		Object [] values = new Object[]{idOwner};
		jdbcTemplate.update(sqlQueries.deleteOwner(), values);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#editOwner(java.util.Map)
	 */
	public void editOwner(Owner owner) {
		Object [] values = new Object[]{owner.getName(), owner.getINN(), owner.getAddres(), owner.getId()};
		String sqlQuery = sqlQueries.editOwner();
		jdbcTemplate.update(sqlQuery, values);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#reviewOwner(java.util.Map)
	 */
	public Owner reviewOwner(int id) {
		Object [] values = new Object[]{id};
		List<Organization> resultSet = jdbcTemplate.query(sqlQueries.reviewOwner(), values, organizationRowMapper);
		return  resultSet.get(0);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#createOwner(java.util.Map)
	 */
	public void createPassport(Passport passport) {
		Object [] values = new Object[]{passport.getIdOwner(), passport.getRegion(), passport.getCadastrNumber(), 
										passport.getArea(), passport.getType(), passport.getComment()};
		jdbcTemplate.update(sqlQueries.createPassport(), values);
		
		//������� ������ ��� ����������� ������� �� ������������� Id ��������� ������ �����������
		passport.setID(getPassportwithMaxId(passport.getIdOwner()));
		
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� ���������� ����
		addPassportEvent(passport, owner, ADD_EVENT);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#deleteOwner(java.util.Map)
	 */
	public void deletePassport(int id) {
		Object [] values = new Object[]{id};
		Passport passport = reviewPassport(id);
		jdbcTemplate.update(sqlQueries.deletePassport(), values);
		
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� �������� ����
		addPassportEvent(passport, owner, DELETE_EVENT);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#editOwner(java.util.Map)
	 */
	public void editPassport(Passport passport) {
		Object [] values = new Object[]{passport.getIdOwner(), passport.getRegion(), passport.getCadastrNumber(), passport.getArea(), passport.getType(), passport.getComment(), passport.getID()};
		jdbcTemplate.update(sqlQueries.editPassport(), values);

		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� �������������� ����
		addPassportEvent(passport, owner, EDIT_EVENT);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#reviewOwner(java.util.Map)
	 */
	public Passport reviewPassport(int id) {
		Object[] values = new Object[] {id};
		List<Passport> resultSet = jdbcTemplate.query(sqlQueries.reviewPassport(), values , passportRowMapper);

		Passport passport = resultSet.get(0);
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� ��������� ����
		addPassportEvent(resultSet.get(0), owner, REVIEW_EVENT);
		return passport;
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#reviewAllPassports()
	 */
	public List<Passport> reviewAllPassports() {
		List<Passport> resaltList = jdbcTemplate.query(sqlQueries.reviewAllPassports(), passportRowMapper);
		
		for(Passport passport : resaltList){
			Owner owner = reviewOwner(passport.getIdOwner());
			passport.setOwner(owner);
		}
		
		return resaltList;
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#findPassports(java.util.Map)
	 */
	public List<Passport> findPassports(Map<String, String> info) {
		List<Passport> resaltList = jdbcTemplate.query(sqlQueries.findPassports(info), passportRowMapper);
		
		for(Passport passport : resaltList){
			Owner owner = reviewOwner(passport.getIdOwner());
			passport.setOwner(owner);
		}
		
		return resaltList;
	}
	
	/**
	 * �������� ������ ������� � ���� ������
	 */
	private void addPassportEvent(Passport passport, Owner owner, String TypeEvent) {
		
		PassportEvent event = new PassportEvent(passport, owner, TypeEvent);
		Object[] values = new Object[] {event.getIdPassport(), event.getIdAuthor(), event.getMessage(), event.getType()};
		String sqlQuery = sqlQueries.createPassportEvent();
		jdbcTemplate.update(sqlQuery, values);
	}
	
	/**
	 * �������� ������ ������� �� ��
	 * @param idEvent - id ���������� �������
	 */
	public void deletePassportEvent(int idEvent){
		jdbcTemplate.update(sqlQueries.deletePassportEvent(), new Object[]{idEvent});
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.DAO#reviewAllPassportEvent()
	 */
	public List<PassportEvent> reviewAllPassportEvent(){
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = jdbcTemplate.query(sqlQueries.reviewAllPassportEvent(), eventRowMapper);
		return events;
	}
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.DAO#reviewAllOwnerEvents(int)
	 */
	public List<PassportEvent> reviewAllOwnerEvents(int idOwner) {
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = jdbcTemplate.query(sqlQueries.reviwAllOwnerPassportEvent(), new Object[]{idOwner}, eventRowMapper);
		return events;
	}

	/**
	 * �������� id �������� �����������, ������� �������� ������������ ����� ���� id ��������� ���� �����������
	 * @param idOwner - id ���������
	 * @return - id
	 */
	private int getPassportwithMaxId(int idOwner) {
		return jdbcTemplate.queryForInt(sqlQueries.getMAXidPassportByOwner(), new Object[]{idOwner});
	}

	/*
	 * @see ru.lenoblgis.trenning.agrocultural.dataTier.accessTODataServices.DAO#authorization(java.lang.String, java.lang.String)
	 */
	public User authorization(String login, String password) {
		Object [] values = new Object[]{login, password};
		List<User> user = jdbcTemplate.query(sqlQueries.authorization(), values, userRowMapper);
		if(user.isEmpty()){
			return null;
		}else{
			return user.get(0);
		}
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.DAO#registration(java.lang.String, java.lang.String)
	 */
	public User registration(String login, String password, int idOrganization) {
		Object [] values = new Object[]{login, password, idOrganization};
		jdbcTemplate.update(sqlQueries.registration(), values);
		return authorization(login, password);
	}

}
