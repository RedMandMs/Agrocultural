package ru.lenoblgis.introduse.sergey.data.dao.sqlQueries;

import java.util.Map;

import ru.lenoblgis.introduse.sergey.domen.actionevent.PassportEvent;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;
import ru.lenoblgis.introduse.sergey.domen.user.User;

public class SQLServerQueries implements SQLQueries {

	public final static String NAME_ORG_TABLE = "organization_table";
	public final static String NAME_FIELD_TABLE = "field_table";
	public final static String NAME_EVENT_TABLE = "event_passport_table";
	public final static String NAME_USER_TABLE = "users";
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#createOwner()
	 */
	public String createOwner(Owner owner) {
		return "INSERT INTO " + NAME_ORG_TABLE + "(name, inn, address_org) VALUES('" + owner.getName() + "', "+owner.getInn()+", '"+owner.getAddress()+"')";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#deleteOwner()
	 */
	public String deleteOwner() {
		return "DELETE FROM " + NAME_ORG_TABLE
				+ " WHERE (id = ?)";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#editOwner(java.util.Map)
	 */
	public String editOwner() {
		return "UPDATE " + NAME_ORG_TABLE
				+ " SET "
				+ " name = ? , "
				+ " inn = ? , "
				+ " address_org = ? "
				+ " WHERE (id = ?)";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#reviewOwner()
	 */
	public String reviewOwner() {
		return "SELECT * "
				+ " FROM " + NAME_ORG_TABLE
				+ " WHERE (id = ?)";
	}
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#findOwnerByINN(java.lang.Integer)
	 */
	public String findOwnerByINN(){
		return "SELECT * "
				+ " FROM " + NAME_ORG_TABLE
				+ " WHERE (inn = ?)";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#findOwnerByName(java.lang.String)
	 */
	public String findOwnerByName(String name){
		return "SELECT * "
				+ " FROM " + NAME_ORG_TABLE
				+ " WHERE (name LIKE '"+name+"');";
	}
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#createPassport()
	 */
	public String createPassport(Passport passport) {
		return "INSERT INTO " + NAME_FIELD_TABLE + "(id_organization, region, cadastr_number, area, type_field, comment) "
						+ "VALUES("+passport.getIdOwner()+", '"+passport.getRegion()+"', "+passport.getCadastrNumber()+", "
								+ passport.getArea()+",'"+passport.getType()+"', '"+passport.getComment()+"')";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#deletePassport()
	 */
	public String deletePassport() {
		return "DELETE FROM " + NAME_FIELD_TABLE
				+ " WHERE (id = ?); ";
	}

	/**
	 * (non-Javadoc)
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#editPassport(java.util.Map)
	 */
	public String editPassport() {
		return "UPDATE " + NAME_FIELD_TABLE
				+ " SET "
				+ " id_organization = ?, "
				+ " region = ?, "
				+ " cadastr_number = ?, "
				+ " area = ?, "
				+ " type_field = ?, "
				+ " comment = ? "
				+ " WHERE (id = ?);";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#reviewPassport()
	 */
	public String reviewPassport() {
		String query = "SELECT * "
				+ " FROM " + NAME_FIELD_TABLE
				+ " WHERE (id = ?);";
		return query;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#reviewAllPassports()
	 */
	public String reviewAllPassports() {
		return "SELECT * "
				+ " FROM " + NAME_FIELD_TABLE;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#findPassports(java.util.Map)
	 */
	public String findPassports(Map<String, Object> info) {
		String query = "SELECT * FROM " + NAME_FIELD_TABLE + " ";
		String condition = "";
		
		Integer id = (Integer) info.get("id");
		if(id != null && id != 0) condition = condition + "id = " + id;
		Integer idOrganization = (Integer) info.get("id_organization");
		if(idOrganization != null && idOrganization != 0) condition = condition + " AND id_organization = " + idOrganization;
		String region = (String) info.get("region");
		if(region != null && !(region.trim().equals(""))) condition = condition + " AND region = '" + region + "'";
		Integer cadastrNumber = (Integer) info.get("cadastr_number");
		if(cadastrNumber != null && cadastrNumber != 0) condition = condition + " AND cadastr_number = " + cadastrNumber;
		Float area = (Float) info.get("area");
		if(area != null && area != 0) condition = condition + " AND area = " + area;
		String type = (String) info.get("type_field");
		if(type != null && !(type.trim().equals(""))) condition = condition + " AND type_field = '" + type + "'";
		String comment = (String) info.get("comment");
		if(comment != null && !(comment.trim().equals(""))) condition = condition + " AND comment LIKE '" + comment + "'";
		
		String [] withoutAnd = condition.split(" ", 3);
		if(withoutAnd.length != 1 && withoutAnd[1].equals("AND")) condition = withoutAnd[2];
		if(condition.equals("")){
			query = query + ";";
		}else{
			query = query + " WHERE(" + condition + ");";
		}
		return query;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#createPassportEvent()
	 */
	public String createPassportEvent() {
		return "INSERT INTO " + NAME_EVENT_TABLE + "(id_passport, id_organization, message_event, date_time_event, type_event) VALUES(?,?,?,GETDATE(),?);";
	}
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#createPassportEvent()
	 */
	public String deletePassportEvent() {
		return "DELETE FROM " + NAME_EVENT_TABLE + " WHERE id = ?;";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#reviewAllPassportEvent()
	 */
	public String reviewAllPassportEvent() {
		return "SELECT * FROM " + NAME_EVENT_TABLE + " ORDER BY id DESC";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#reviwAllOwnerPassportEvent()
	 */
	public String reviwAllOwnerPassportEvent() {
		return "SELECT * FROM " + NAME_EVENT_TABLE + " WHERE id_organization = ? ORDER BY id DESC;";
	}

	@Override
	public String authorization() {
		return "SELECT * FROM " + NAME_USER_TABLE + " WHERE username = ? AND password = ?;";
	}

	@Override
	public String createUser(User user) {
		return "INSERT INTO " + NAME_USER_TABLE + " (username, password, id_organization, role, enabled) VALUES('"+user.getLogin()+"', '"+user.getPassword()+"', "+user.getOrganizationId()+", '" +user.getRoleStr()+"', 1)";
	}

	@Override
	public String reviewUserByLogin() {
		return "SELECT * FROM " + NAME_USER_TABLE + " WHERE username = ?";
	}

	@Override
	public String findOwners(Organization findingOrganization) {
		
		String query = "SELECT * FROM " + NAME_ORG_TABLE + " ";
		String condition = "";
		
		Integer id = findingOrganization.getId();
		if(id != null && id != 0) condition = condition + "id = " + id;
		String name = findingOrganization.getName();
		if(name != null && !(name.trim().equals(""))) condition = condition + " AND name LIKE '" + name.trim()+"'";
		Integer inn = findingOrganization.getInn();
		if(inn != null && inn != 0) condition = condition + " AND inn = " + inn;
		String address = findingOrganization.getAddress();
		if(address != null && !(address.trim().equals(""))) condition = condition + " AND address_org LIKE '" + address + "'";
		
		String [] withoutAnd = condition.split(" ", 3);
		if(withoutAnd.length != 1 && withoutAnd[1].equals("AND")) condition = withoutAnd[2];
		if(condition.equals("")){
			query = query + ";";
		}else{
			query = query + " WHERE(" + condition + ");";
		}
		return query;
	}

	@Override
	public String findEvents(PassportEvent findingEvent) {
		String query = "SELECT * FROM " + NAME_EVENT_TABLE + " ";
		String condition = "";
		
		Integer id = findingEvent.getId();
		if(id != null && id != 0) condition = condition + "id = " + id;
		String typeEvent = findingEvent.getType();
		if(typeEvent != null && !(typeEvent.trim().equals(""))) condition = condition + " AND type_event LIKE '" + typeEvent.trim()+"'";
		Integer idAuthor = findingEvent.getIdAuthor();
		if(idAuthor != null && idAuthor != 0) condition = condition + " AND id_organization = " + idAuthor;
		Integer idPassport = findingEvent.getIdPassport();
		if(idPassport != null && idPassport != 0) condition = condition + " AND id_passport = " + idPassport;
		
		String [] withoutAnd = condition.split(" ", 3);
		if(withoutAnd.length != 1 && withoutAnd[1].equals("AND")) condition = withoutAnd[2];
		if(!condition.equals("")){
			query = query + " WHERE(" + condition + ") ";
		}
		return query + " ORDER BY id DESC;";
	}
	
	
	
}
