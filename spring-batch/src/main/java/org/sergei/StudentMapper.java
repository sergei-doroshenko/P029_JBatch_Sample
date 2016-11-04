package org.sergei;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<StudentModel> {

	public StudentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		StudentModel student = new StudentModel();
		student.setFirstName(rs.getString("firstName"));
		student.setLastName(rs.getString("lastName"));
		student.setSchool(rs.getString("school"));
		student.setRollNumber(rs.getInt("rollNumber"));
		return student;
	}

}
