package com.personal.pharmacy.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return ingredientServices.findById(id).isEmpty()
		? new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND)
		: new ResponseEntity<Ingredient>(ingredientServices.findById(id).get(), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
		if (ingredientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		ingredientServices.delete(ingredientOptional.get());
		return new ResponseEntity<String>("Deleted ingredient with id " + id, HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> add(Ingredient ingredient, BindingResult bindingResult) {
		
		if (bindingResult.hasFieldErrors()) {
			List<String> errorStrings = new ArrayList<>();
			bindingResult.getFieldErrors().forEach(objectError -> {
				errorStrings.add(objectError.getDefaultMessage());
			});
			
			return new ResponseEntity<String>(errorStrings.toString(), HttpStatus.BAD_REQUEST);
		}
		
		Ingredient savedIngredient = ingredientServices.save(ingredient);
		return new ResponseEntity<Ingredient>(savedIngredient, HttpStatus.CREATED);
	}
	
	@PatchMapping("{id}/updatename")
	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody String name){
		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
		if (ingredientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		Ingredient ingredient = ingredientOptional.get();
		ingredientServices.updateIngredientName(ingredient, name);
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.ACCEPTED);
	}
	
}
