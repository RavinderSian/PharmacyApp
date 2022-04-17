package com.personal.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.personal.pharmacy.mappers.IngredientRowMapper;
import com.personal.pharmacy.model.Ingredient;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Ingredient save(Ingredient ingredient) {
		KeyHolder holder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO ingredient (name, medicine_id, creation_timestamp, updated_timestamp)"
						+ " values(?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, ingredient.getName());
				
				if (ingredient.getMedicineId() == null) {
					ps.setNull(2, Types.BIGINT);
				}else {
					ps.setFloat(2, ingredient.getMedicineId());
				}
				
				return ps;
			}
		}, holder);
		
		Number newUserId = (Long) holder.getKeys().get("id");
		
		ingredient.setIngredientId(newUserId.longValue());
		return ingredient;
	}

	@Override
	public Integer deleteById(Long id) {
		return jdbcTemplate.update("DELETE FROM ingredient WHERE id=?", id);
	}

	@Override
	public Optional<Ingredient> findById(Long id) {
		try {
			return 	Optional.of(jdbcTemplate.queryForObject("SELECT * FROM ingredient WHERE id=?", new IngredientRowMapper(), id));
		}catch(EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}
	
	public Integer updateName(Long id, String name) {
		
		return jdbcTemplate.update("UPDATE ingredient SET name='" + name
				+ "', updated_timestamp = CURRENT_TIMESTAMP WHERE id =" + id);
	}

	@Override
	public List<Ingredient> findIngredientsByMedicine(Long id) {
			
		return jdbcTemplate.query("SELECT * FROM ingredient WHERE medicine_id = " + id, new IngredientRowMapper());

	}

}
