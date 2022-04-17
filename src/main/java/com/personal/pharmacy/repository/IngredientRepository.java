package com.personal.pharmacy.repository;

import java.util.List;

import com.personal.pharmacy.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	Integer updateName(Long id, String name);
	List<Ingredient> findIngredientsByMedicine(Long id);
	
}
