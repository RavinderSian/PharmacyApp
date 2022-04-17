package com.personal.pharmacy.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.services.MedicineService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("medicine/")
public class MedicineController implements CrudController<Medicine, Long> {
	
	private final MedicineService service;
	
	public MedicineController(MedicineService medicineService) {
		this.service = medicineService;
	}

	@Override
	public ResponseEntity<?> getById(Long id){
		
		Optional<Medicine> medicineOptional = service.findById(id);
		
		return medicineOptional.isEmpty()
		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(medicineOptional.get(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> deleteById(Long id){
		if (service.delete(id) == 0) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	 
	@Override
	public ResponseEntity<?> add(Medicine medicine, BindingResult bindingResult){
		if (bindingResult.hasFieldErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		service.save(medicine);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("{id}/updatename")
	public ResponseEntity<?> updateMedicineName(@PathVariable Long id, @RequestBody String name){
		
		if (service.updateName(id, name) == 0) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("{id}/ingredients")
	public ResponseEntity<?> getIngredients(@PathVariable Long id){
		
		List<Ingredient> ingredients = service.findIngredientsByMedicine(id);
		
		if(ingredients.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(ingredients , HttpStatus.OK);
	}
	
}
