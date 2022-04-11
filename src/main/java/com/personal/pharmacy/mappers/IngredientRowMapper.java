package com.personal.pharmacy.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personal.pharmacy.model.Ingredient;

public class IngredientRowMapper implements RowMapper<Ingredient> {

	@Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(rs.getLong("id"));
		ingredient.setName(rs.getString("name"));
		ingredient.setCreatedTime(rs.getTimestamp("creation_timestamp"));
		ingredient.setUpdatedTime(rs.getTimestamp("updated_timestamp"));
		
		return ingredient;
	}

}
