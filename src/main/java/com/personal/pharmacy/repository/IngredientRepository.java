package com.personal.pharmacy.repository;

import com.personal.pharmacy.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	Integer updateName(Long id, String name);
	
}
