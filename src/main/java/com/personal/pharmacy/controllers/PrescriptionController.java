package com.personal.pharmacy.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.PrescriptionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("prescription/")
public class PrescriptionController implements CrudController<Prescription, Long> {

	private final PrescriptionService prescriptionService;
	
	public PrescriptionController(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> getById(Long id){
		
		Optional<Prescription> prescriptionOptional = prescriptionService.findById(id);
		
		return prescriptionOptional.isEmpty()
		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(prescriptionOptional.get(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> add (Prescription prescription, BindingResult bindingResult){
		if (bindingResult.hasFieldErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		prescriptionService.save(prescription);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> deleteById(Long id){
		if (prescriptionService.delete(id) == 0) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
