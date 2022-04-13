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

import com.personal.pharmacy.mappers.PrescriptionRowMapper;
import com.personal.pharmacy.model.Prescription;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Prescription save(Prescription prescription) {
		KeyHolder holder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				prescription.setCreatedTime();
				prescription.setUpdatedTime();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO prescription (creation_timestamp, updated_timestamp) values(?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setTimestamp(1, prescription.getCreatedTime());
				ps.setTimestamp(2, prescription.getUpdatedTime());
				return ps;
			}
		}, holder);
		
		Number newUserId = (Integer) holder.getKeys().get("id");
		
		prescription.setPrescriptionId(newUserId.longValue());
		return prescription;
	}

	@Override
	public Integer deleteById(Long id) {
		return jdbcTemplate.update("DELETE FROM prescription WHERE id=?", id);
	}

	@Override
	public Optional<Prescription> findById(Long id) {
		try {
			return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM prescription WHERE id=?", new PrescriptionRowMapper(), id));
		}catch(EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}

}
