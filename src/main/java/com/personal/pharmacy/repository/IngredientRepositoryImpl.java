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
				if (ingredient.getCreatedTime() == null) {
					ingredient.setCreatedTime();
				}				
				ingredient.setUpdatedTime();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO ingredient (name, creation_timestamp, updated_timestamp) values(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, ingredient.getName());
				ps.setTimestamp(2, ingredient.getCreatedTime());
				ps.setTimestamp(3, ingredient.getUpdatedTime());
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
				+ "', updated_timestamp='" + Timestamp.valueOf(LocalDateTime.now())
				+ "' WHERE id =" + id);
	}

}
