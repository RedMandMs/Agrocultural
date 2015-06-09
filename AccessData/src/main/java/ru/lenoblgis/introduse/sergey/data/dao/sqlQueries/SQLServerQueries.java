package ru.lenoblgis.introduse.sergey.data.dao.sqlQueries;

import java.util.Map;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
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
		String query = "SELECT * FROM " + NAME_FIELD_TABLE + " WHERE (";
		String condition = "";
		
		Integer id = (Integer) info.get("id");
		if(id != null && id != 0) condition = condition + "id = " + id;
		Integer idOrganization = (Integer) info.get("id_organization");
		if(idOrganization != null && idOrganization != 0) condition = condition + " AND id_organization = " + idOrganization;
		String region = (String) info.get("region");
		if(region != null && region.trim() != "") condition = condition + " AND region = '" + region + "'";
		Integer cadastrNumber = (Integer) info.get("cadastr_number");
		if(cadastrNumber != null && cadastrNumber != 0) condition = condition + " AND cadastr_number = " + cadastrNumber;
		Float area = (Float) info.get("area");
		if(area != null && area != 0) condition = condition + " AND area = " + area;
		String type = (String) info.get("type_field");
		if(region != null && region.trim() != "") condition = condition + " AND type_field = '" + type + "'";
		String comment = (String) info.get("comment");
		if(comment != null && comment.trim() != "") condition = condition + " AND comment LIKE '" + comment + "'";
		
		String [] withoutAnd = condition.split(" ", 3);
		if(withoutAnd[1].equals("AND")) condition = withoutAnd[2];
		query = query + condition + ");";
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
		return "SELECT * FROM " + NAME_EVENT_TABLE;
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#reviwAllOwnerPassportEvent()
	 */
	public String reviwAllOwnerPassportEvent() {
		return "SELECT * FROM " + NAME_EVENT_TABLE + " WHERE id_organization = ?;";
	}

	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#getMAXidPassportByOwner()
	 */
	public String getMAXidPassportByOwner(){
		return "SELECT MAX(id) AS maxId FROM " + NAME_FIELD_TABLE + " WHERE id_organization = ?;";
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
	
	
	
}
