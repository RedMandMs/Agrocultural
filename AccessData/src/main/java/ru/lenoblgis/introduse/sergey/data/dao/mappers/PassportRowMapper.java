package ru.lenoblgis.introduse.sergey.data.dao.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.lenoblgis.introduse.sergey.data.domen.passport.Passport;

public class PassportRowMapper implements RowMapper<Passport>, Serializable {

	/**
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Passport mapRow(ResultSet rs, int rowNum) throws SQLException {		
		return new Passport(rs.getInt("id"), rs.getInt("id_organization"), rs.getString("region"), 
				rs.getString("cadastr_number"), rs.getInt("area"), rs.getString("type_field"), rs.getString("comment"));
	}

}
