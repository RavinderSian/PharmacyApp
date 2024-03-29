package com.personal.pharmacy.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personal.pharmacy.model.Patient;

public class PatientRowMapper implements RowMapper<Patient> {

	@Override
	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient = new Patient();
		patient.setPatientId(rs.getLong("Id"));
		patient.setFirstName(rs.getString("first_name"));
		patient.setLastName(rs.getString("last_name"));
		patient.setCreatedTime(rs.getTimestamp("creation_timestamp"));
		patient.setUpdatedTime(rs.getTimestamp("updated_timestamp"));
		
		return patient;
	}

}
