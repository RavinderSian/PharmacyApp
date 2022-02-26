package com.personal.pharmacy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("ingredient/")
public class IngredientController {

//	private final IngredientService ingredientServices;
//
//	public IngredientController(IngredientService ingredientServices) {
//		this.ingredientServices = ingredientServices;
//	}
//
//	@Override
//	public ResponseEntity<?> getById(Long id) {
//		return ingredientServices.findById(id).isEmpty()
//		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//		: new ResponseEntity<>(ingredientServices.findById(id).get(), HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<?> deleteById(Long id) {
//		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
//		if (ingredientOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		ingredientServices.delete(ingredientOptional.get());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<?> add(Ingredient ingredient, BindingResult bindingResult) {
//		if (bindingResult.hasFieldErrors()) {
//			Map<String, String> errors = new HashMap<>();
//			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//		}
//		ingredientServices.save(ingredient);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@PatchMapping("{id}/updatename")
//	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody String name){
//		Optional<Ingredient> ingredientOptional = ingredientServices.findById(id);
//		if (ingredientOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		Ingredient ingredient = ingredientOptional.get();
//		ingredientServices.updateIngredientName(ingredient, name);
//		return new ResponseEntity<>(ingredient, HttpStatus.OK);
//	}
	
}
