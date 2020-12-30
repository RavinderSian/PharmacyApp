package com.personal.pharmacy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.services.IngredientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("ingredient/")
public class IngredientController implements CrudController<Ingredient, Long> {

	private final IngredientService ingredientServices;

	@Override
	public ResponseEntity<?> getById(Long id) {
		Ingredient ingredient = ingredientServices.findById(id);
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		Ingredient ingredient = ingredientServices.findById(id);
		ingredientServices.delete(ingredient);
		return new ResponseEntity<String>("Deleted ingredient with id " + id, HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> add(Ingredient ingredient) {
		Ingredient savedIngredient = ingredientServices.save(ingredient);
		return new ResponseEntity<Ingredient>(savedIngredient, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("{id}/updateName")
	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody String name){
		Ingredient ingredient = ingredientServices.findById(id);
		Ingredient updatedIngredient = ingredientServices.updateIngredientName(ingredient, name);
		return new ResponseEntity<Ingredient>(updatedIngredient, HttpStatus.ACCEPTED);
	}
}
