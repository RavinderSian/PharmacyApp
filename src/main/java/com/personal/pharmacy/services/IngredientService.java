package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Ingredient;

public interface IngredientService extends CrudService<Ingredient, Long> {

	Integer updateIngredientName(Long id, String name);
}
