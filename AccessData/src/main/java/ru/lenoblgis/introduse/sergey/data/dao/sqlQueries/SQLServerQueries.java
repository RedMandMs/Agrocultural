package ru.lenoblgis.introduse.sergey.data.dao.sqlQueries;

import java.util.Map;

public class SQLServerQueries implements SQLQueries {

	public final static String NAME_ORG_TABLE = "organization_table";
	public final static String NAME_FIELD_TABLE = "field_table";
	public final static String NAME_EVENT_TABLE = "event_passport_table";
	public final static String NAME_USER_TABLE = "users_table";
	
	/**
	 * @see ru.lenoblgis.introduse.sergey.data.dao.sqlQueries.SQLQueries#createOwner()
	 */
	public String createOwner() {
		return "INSERT INTO " + NAME_ORG_TABLE + "(name, inn, address_org) VALUES(?,?,?)";
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
	public String createPassport() {
		return "INSERT INTO " + NAME_FIELD_TABLE + "(id_organization, region, cadastr_number, area, type_field, comment) VALUES(?,?,?,?,?,?)";
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
		return "SELECT * "
				+ " FROM " + NAME_FIELD_TABLE
				+ " WHERE (id = ?);";
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
	public String findPassports(Map<String, String> info) {
		String query = "SELECT * FROM " + NAME_FIELD_TABLE + " WHERE (";
		String condition = "";
		
		if(info.containsKey("id")) condition = condition + "id = " + Integer.parseInt(info.get("id"));
		if(info.containsKey("id_organization")) condition = condition + " AND id_organization = " + Integer.parseInt(info.get("id_organization"));
		if(info.containsKey("region")) condition = condition + " AND region = '" + info.get("region") + "'";
		if(info.containsKey("cadastr_number")) condition = condition + " AND cadastr_number = " + Integer.parseInt(info.get("cadastr_number"));
		if(info.containsKey("area")) condition = condition + " AND area = " + Integer.parseInt(info.get("area"));
		if(info.containsKey("type_field")) condition = condition + " AND type_field = '" + info.get("type_field") + "'";
		if(info.containsKey("comment")) condition = condition + " AND comment LIKE '" + info.get("comment") + "'";
		
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
		return "SELECT * FROM " + NAME_USER_TABLE + " WHERE user_login = ? AND user_password = ?;";
	}

	@Override
	public String registration() {
		return "INSERT INTO " + NAME_USER_TABLE + " (user_login, user_password, id_organization) VALUES(?, ?, ?)";
	}
	
}
