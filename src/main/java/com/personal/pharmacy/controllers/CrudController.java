package com.personal.pharmacy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudController<T, ID> {

	@GetMapping("{id}")
	ResponseEntity<?> getById(@PathVariable ID id);
	
	@GetMapping("delete/{id}")
	ResponseEntity<?> deleteById(@PathVariable ID id);
	
	@PostMapping("save")
	ResponseEntity<?> add(@RequestBody T t);
}
