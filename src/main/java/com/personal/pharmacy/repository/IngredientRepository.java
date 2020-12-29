package com.personal.pharmacy.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.pharmacy.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

	
}
