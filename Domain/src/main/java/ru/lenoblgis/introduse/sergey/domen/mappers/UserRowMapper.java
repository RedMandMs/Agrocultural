package ru.lenoblgis.introduse.sergey.domen.mappers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.domen.user.UserRole;

public class UserRowMapper implements RowMapper<User>, Serializable{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("id_organization"));
		user.setRole(UserRole.valueOf(rs.getString("role")));	
		return user;
		}
	
	

}
