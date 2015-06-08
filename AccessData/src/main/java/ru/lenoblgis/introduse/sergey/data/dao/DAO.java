package ru.lenoblgis.introduse.sergey.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries;
import ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLServerQueries;
import ru.lenoblgis.introduse.sergey.domen.actionevent.PassportEvent;
import ru.lenoblgis.introduse.sergey.domen.mappers.EventRowMapper;
import ru.lenoblgis.introduse.sergey.domen.mappers.OrganizationRowMapper;
import ru.lenoblgis.introduse.sergey.domen.mappers.PassportRowMapper;
import ru.lenoblgis.introduse.sergey.domen.mappers.UserRowMapper;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.domen.user.User;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Component("dao")
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
	SQLServerDataSource ds;
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
	 * ������� ���������
	 * @param owner - ����� ��������
	 * @return - id ������ ���������
	 */
	private int createOwner(Owner owner) {
		String sqlQuery = sqlQueries.createOwner(owner);
		PreparedStatementCreator psc = new PrepereStmCreater(sqlQuery);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return keyHolder.getKey().intValue();
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
		Object [] values = new Object[]{owner.getName(), owner.getInn(), owner.getAddress(), owner.getId()};
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
	public int createPassport(Passport passport) {
		String sqlQuery = sqlQueries.createPassport(passport);
		PreparedStatementCreator psc = new PrepereStmCreater(sqlQuery);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int id = jdbcTemplate.update(psc, keyHolder);
		
		//������� ������ ��� ����������� ������� �� ������������� Id ��������� ������ �����������
		passport.setId(id);
		
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� ���������� ����
		addPassportEvent(passport, owner, ADD_EVENT);
		
		return id;
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
		Object [] values = new Object[]{passport.getIdOwner(), passport.getRegion(), passport.getCadastrNumber(), passport.getArea(), passport.getType(), passport.getComment(), passport.getId()};
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
	public List<Passport> findPassports(Map<String, Object> info) {
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
	private void addPassportEvent(Passport passport, Owner owner, String typeEvent) {
		
		PassportEvent event = new PassportEvent(passport, owner, typeEvent);
		
		//������������ ���������, ������� ��������� � ��
		String message = "����������� \"" + owner.getName() + "\" (id = " + owner.getId() + ") " + event.getTypeEvent().getWorldForMassege() 
								+ " �������� � id = " + passport.getId();
		event.setMessage(message);
		
		Object[] values = new Object[] {event.getIdPassport(), event.getIdAuthor(), message, event.getType()};
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
	public User reviewUser(User user) {
		Object [] values = new Object[]{user.getLogin(), user.getPassword()};
		List<User> users = jdbcTemplate.query(sqlQueries.authorization(), values, userRowMapper);
		if(users.isEmpty()){
			return null;
		}else{
			return users.get(0);
		}
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.DAO#registration(java.lang.String, java.lang.String)
	 */
	public User registration(User user, Owner organization) {
		int organizationId = createOwner(organization);
		user.setOrganizationId(organizationId);
		int userId = createUser(user);
		user.setId(userId);
		return user;
	}
	
	/**
	 * �������� ������������ � �������
	 * @param user - ������������
	 * @return - id ������������
	 */
	private int createUser(User user){
		String sqlQuery = sqlQueries.createUser(user);
		PreparedStatementCreator psc = new PrepereStmCreater(sqlQuery);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public User findUserByLogin(String login){
		
		Object[] values = new Object[] {login};
		List<User> resultSet = jdbcTemplate.query(sqlQueries.reviewUserByLogin(), values , userRowMapper);
				
		return resultSet.get(0);
	}
	
	/**
	 * ����� ��� ���������� sql ��������, ��� �������� ����, ������� ������ ������������ ��� �������
	 * @author Sergey
	 *
	 */
	private class PrepereStmCreater implements PreparedStatementCreator{

		String sqlQuery = null;

		public PrepereStmCreater(String sqlQuery) {
			super();
			this.sqlQuery = sqlQuery;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			PreparedStatement pstm = con.prepareStatement(sqlQuery, new String [] {"id"});
			
			return pstm;
		}
		 
	}


}
