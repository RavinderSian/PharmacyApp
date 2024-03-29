package com.personal.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.personal.pharmacy.mappers.PatientRowMapper;
import com.personal.pharmacy.model.Patient;

@Repository
public class PatientRepositoryImpl implements PatientRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Patient save(Patient patient) {
		KeyHolder holder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO patient (first_name, last_name,"
						+ " creation_timestamp, updated_timestamp) values(?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, patient.getFirstName());
				ps.setString(2, patient.getLastName());
				return ps;
			}
		}, holder);
		
		Number newUserId = (Long) holder.getKeys().get("id");
		
		patient.setPatientId(newUserId.longValue());
		return patient;
	}

	@Override
	public Integer deleteById(Long id) {
		return jdbcTemplate.update("DELETE FROM patient WHERE id=?", id);
	}

	@Override
	public Optional<Patient> findById(Long id) {
		try {
			return 	Optional.of(jdbcTemplate.queryForObject("SELECT * FROM patient WHERE id=?", new PatientRowMapper(), id));
		}catch(EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}
	
	@Override
	public Integer updateFirstName(Long id, String firstName) {
		
		return jdbcTemplate.update("UPDATE patient SET first_name='" + firstName
				+ "', updated_timestamp = CURRENT_TIMESTAMP WHERE id =" + id);
	}

}
