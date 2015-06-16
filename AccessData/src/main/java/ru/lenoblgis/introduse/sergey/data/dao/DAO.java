package ru.lenoblgis.introduse.sergey.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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
	 * �����
	 */
	 private static final Logger log = Logger.getLogger(DAO.class);
	 
	
	/**
	 * ��������� ����� ������������ ��� ���������� ������� - ���������� ����
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
		try{
			ds = new SQLServerDataSource();
			ds.setPortNumber(1433);
			ds.setHostNameInCertificate("localhost");
			ds.setDatabaseName("passport_agricultural");
			ds.setUser("adminAgricultural");
			ds.setPassword("admin123");
			jdbcTemplate = new JdbcTemplate(ds);
		}catch(Exception ex){
			log.log(Level.ERROR, "DAO unable to connect to database");
		}
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
		int id = keyHolder.getKey().intValue();
		owner.setId(id);
		log.log(Level.INFO, "Created organization: " + owner);
		return id;
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#deleteOwner(java.util.Map)
	 */
	public void deleteOwner(int idOwner) {
		Object [] values = new Object[]{idOwner};
		jdbcTemplate.update(sqlQueries.deleteOwner(), values);
		log.log(Level.INFO, "Deleted organization with id = " + idOwner);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#editOwner(java.util.Map)
	 */
	public void editOwner(Owner owner) {
		Object [] values = new Object[]{owner.getName(), owner.getInn(), owner.getAddress(), owner.getId()};
		String sqlQuery = sqlQueries.editOwner();
		jdbcTemplate.update(sqlQuery, values);
		log.log(Level.INFO, "Edited organization: " + owner);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#reviewOwner(java.util.Map)
	 */
	public Owner reviewOwner(int id) {
		Object [] values = new Object[]{id};
		List<Organization> resultSet = jdbcTemplate.query(sqlQueries.reviewOwner(), values, organizationRowMapper);
		Owner owner = resultSet.get(0);
		log.log(Level.INFO, "Reviwed organization: " + owner);
		return  owner;
	}
	
	public List<Organization> findOwnerByINN(Integer inn){
		Object [] values = new Object[]{inn};
		List<Organization> resultSet = jdbcTemplate.query(sqlQueries.findOwnerByINN(), values, organizationRowMapper);
		return resultSet;
	}
	
	public List<Organization> findOwnerByName(String name) {
		List<Organization> resultSet = jdbcTemplate.query(sqlQueries.findOwnerByName(name), organizationRowMapper);
		return resultSet;
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#createOwner(java.util.Map)
	 */
	public int createPassport(Passport passport) {
		String sqlQuery = sqlQueries.createPassport(passport);
		PreparedStatementCreator psc = new PrepereStmCreater(sqlQuery);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		
		int id = keyHolder.getKey().intValue();
		
		//������� ������ ��� ����������� ������� �� ������������� Id ��������� ������ �����������
		passport.setId(id);
		
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� ���������� ����
		addPassportEvent(passport, owner, ADD_EVENT);
		
		log.log(Level.INFO, "Created passport: " + passport);
		
		return id;
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#deleteOwner(java.util.Map)
	 */
	public void deletePassport(int id) {
		Object [] values = new Object[]{id};
		Passport passport = reviewPassportWithoutWrite(id);
		jdbcTemplate.update(sqlQueries.deletePassport(), values);
		
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		//������������ ������� �������� ����
		addPassportEvent(passport, owner, DELETE_EVENT);
		
		log.log(Level.INFO, "Deleted passport: " + passport);
	}
	
	private Passport reviewPassportWithoutWrite(int id){
		Object[] values = new Object[] {id};
		List<Passport> resultSet = jdbcTemplate.query(sqlQueries.reviewPassport(), values , passportRowMapper);

		Passport passport = resultSet.get(0);
		Owner owner = reviewOwner(passport.getIdOwner());
		passport.setOwner(owner);
		
		return passport;
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
		
		log.info("Edited passport: " + passport);
	}

	/**
	 * 
	 * @see dataTier.accessToDataServices.DAO#reviewOwner(java.util.Map)
	 */
	public Passport reviewPassport(int id) {

		Passport passport = reviewPassportWithoutWrite(id);
		
		//������������ ������� ��������� ����
		addPassportEvent(passport, passport.getOwner(), REVIEW_EVENT);
		
		log.error("Reviwed passport: " + passport);
		
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
		
		log.log(Level.INFO, "Reviwed all passports!");
		
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
		
		log.log(Level.INFO, "Executed requare for finding passports");
		
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
		log.log(Level.INFO, "Added event ("+ typeEvent +") about passport: " + passport);
	}
	
	/**
	 * �������� ������ ������� �� ��
	 * @param idEvent - id ���������� �������
	 */
	public void deletePassportEvent(int idEvent){
		jdbcTemplate.update(sqlQueries.deletePassportEvent(), new Object[]{idEvent});
		log.log(Level.INFO, "Event with id="+idEvent+" deleted from data base!");
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.DAO#reviewAllPassportEvent()
	 */
	public List<PassportEvent> reviewAllPassportEvent(){
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = jdbcTemplate.query(sqlQueries.reviewAllPassportEvent(), eventRowMapper);
		log.log(Level.INFO, "Reviwed all passport events");
		return events;
	}
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.DAO#reviewAllOwnerEvents(int)
	 */
	public List<PassportEvent> reviewAllOwnerEvents(int idOwner) {
		List<PassportEvent> events = new ArrayList<PassportEvent>();
		events = jdbcTemplate.query(sqlQueries.reviwAllOwnerPassportEvent(), new Object[]{idOwner}, eventRowMapper);
		log.log(Level.INFO, "Reviwed all passport events of organization with id="+idOwner);
		return events;
	}

	/*
	 * @see ru.lenoblgis.trenning.agrocultural.dataTier.accessTODataServices.DAO#authorization(java.lang.String, java.lang.String)
	 */
	public User reviewUser(User user) {
		Object [] values = new Object[]{user.getLogin(), user.getPassword()};
		List<User> users = jdbcTemplate.query(sqlQueries.authorization(), values, userRowMapper);
		if(users.isEmpty()){
			log.log(Level.INFO, "executed query for finding user: "+user+", but he wasn't find");
			return null;
		}else{
			log.log(Level.INFO, "executed query for finding user: "+user+", and he was find");
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
		log.log(Level.INFO, "Registration user: "+user+ " was execute successful!");
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
		int userId = keyHolder.getKey().intValue();
		log.log(Level.INFO, "User: "+user+" was created successfuk in data base!");
		return userId;
		
	}

	public User findUserByLogin(String login){
		
		Object[] values = new Object[] {login};
		List<User> resultSet = jdbcTemplate.query(sqlQueries.reviewUserByLogin(), values , userRowMapper);
		if(resultSet.isEmpty()){
			log.log(Level.INFO, "Executed finding user by login='"+login+"', but he wasn't find!");
			return null;
		}else{
			log.log(Level.INFO, "Executed finding user by login='"+login+"' and he was find!");
		}
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
