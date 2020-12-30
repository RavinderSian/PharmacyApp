package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Ingredient;

public interface IngredientService extends CrudService<Ingredient, Long> {

	Ingredient updateIngredientName(Ingredient ingredient, String name);
}
