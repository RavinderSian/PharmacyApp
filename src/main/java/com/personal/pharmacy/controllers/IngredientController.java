package com.personal.pharmacy.controllers;

import java.util.Optional;

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
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("ingredient/")
public class IngredientController implements CrudController<Ingredient, Long> {

	private final IngredientService ingredientServices;

	@Override
	public ResponseEntity<?> getById(Long id) {
		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
		if (ingredientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Ingredient>(ingredientOptional.get(), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
		if (ingredientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.ACCEPTED);
		}
		ingredientServices.delete(ingredientOptional.get());
		return new ResponseEntity<String>("Deleted ingredient with id " + id, HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> add(Ingredient ingredient) {
		Ingredient savedIngredient = ingredientServices.save(ingredient);
		return new ResponseEntity<Ingredient>(savedIngredient, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("{id}/updatename")
	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody String name){
		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
		if (ingredientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.ACCEPTED);
		}
		Ingredient ingredient = ingredientOptional.get();
		Ingredient updatedIngredient = ingredientServices.updateIngredientName(ingredient, name);
		return new ResponseEntity<Ingredient>(updatedIngredient, HttpStatus.ACCEPTED);
	}
}
