package ru.lenoblgis.introduse.sergey.data.dao.mappers;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.lenoblgis.introduse.sergey.data.domen.owner.organization.Organization;

public class OrganizationRowMapper implements RowMapper<Organization>, Serializable {

	/**
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Organization(rs.getInt("id"), rs.getString("name"), rs.getInt("inn"), rs.getString("address_org"));
	}

}
