package com.personal.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.personal.pharmacy.mappers.MedicineRowMapper;
import com.personal.pharmacy.model.Medicine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MedicineRepositoryImpl implements MedicineRepository { 

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Medicine save(Medicine medicine) {
		KeyHolder holder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				
				if (medicine.getCreatedTime() == null) {
					medicine.setCreatedTime();
				}
				
				medicine.setUpdatedTime();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO medicine (name, dosage, duration, creation_timestamp, updated_timestamp) values(?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, medicine.getName());
				ps.setInt(2, medicine.getDosage());
				ps.setString(3, medicine.getDuration());
				ps.setTimestamp(4, medicine.getCreatedTime());
				ps.setTimestamp(5, medicine.getUpdatedTime());
				return ps;
			}
		}, holder);
		
		Number newUserId = (Integer) holder.getKeys().get("id");
		
		medicine.setMedicineId(newUserId.longValue());
		return medicine;
	}

	@Override
	public Integer deleteById(Long id) {
		return jdbcTemplate.update("DELETE FROM medicine WHERE id=?", id);
	}

	@Override
	public Optional<Medicine> findById(Long id) {
		try {
			return 	Optional.of(jdbcTemplate.queryForObject("SELECT * FROM medicine WHERE id=?", new MedicineRowMapper(), id));
		}catch(EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}
	
	@Override
	public Integer updateName(Long id, String name) {
		return jdbcTemplate.update("UPDATE medicine SET name='" + name
				+ "', updated_timestamp='" + Timestamp.valueOf(LocalDateTime.now())
				+ "' WHERE id =" + id);
	}

	@Override
	public Optional<Medicine> findByName(String name) {
		
		try {
			return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM medicine WHERE name = '" + name + "'", new MedicineRowMapper()));
		}catch (EmptyResultDataAccessException e) {
			log.info("medicine name not found in database");
			return Optional.empty();
		}
		
	}

}
