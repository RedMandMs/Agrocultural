package ru.lenoblgis.introduse.sergey.data.dao.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import ru.lenoblgis.introduse.sergey.data.domen.actionevent.PassportEvent;

public class EventRowMapper implements RowMapper<PassportEvent>, Serializable{

	/*
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public PassportEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		String dateStr = rs.getDate("date_time_event").toString();
		String timeStr = rs.getTime("date_time_event").toString();
		DateTime dateTimeEvent = DateTime.parse(dateStr + "T" + timeStr);
		return new PassportEvent(rs.getInt("id"), rs.getInt("id_passport"), rs.getInt("id_organization"), 
							rs.getString("message_event"), dateTimeEvent, rs.getString("type_event"));
	}

}
