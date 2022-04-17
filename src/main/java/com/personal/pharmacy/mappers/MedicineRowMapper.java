package com.personal.pharmacy.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personal.pharmacy.model.Medicine;

public class MedicineRowMapper implements RowMapper<Medicine> {

	@Override
	public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
		Medicine medicine = new Medicine();
		medicine.setMedicineId(rs.getLong("id"));
		medicine.setName(rs.getString("name"));
		medicine.setDosage(rs.getInt("dosage"));
		medicine.setDuration(rs.getString("duration"));
		medicine.setCreatedTime(rs.getTimestamp("creation_timestamp"));
		medicine.setUpdatedTime(rs.getTimestamp("updated_timestamp"));
		return medicine;
	}

}
