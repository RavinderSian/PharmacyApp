package com.personal.pharmacy.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personal.pharmacy.model.Prescription;

public class PrescriptionRowMapper  implements RowMapper<Prescription> {

	@Override
	public Prescription mapRow(ResultSet rs, int rowNum) throws SQLException {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(rs.getLong("id"));
		prescription.setCreatedTime(rs.getTimestamp("creation_timestamp"));
		prescription.setUpdatedTime(rs.getTimestamp("updated_timestamp"));
		
		return prescription;
	}

}
