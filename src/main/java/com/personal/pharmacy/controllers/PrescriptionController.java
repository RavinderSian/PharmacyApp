package com.personal.pharmacy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.PrescriptionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("prescription/")
public class PrescriptionController implements CrudController<Prescription, Long> {

	private final PrescriptionService prescriptionService;
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Prescription prescription = prescriptionService.findById(id);
		return new ResponseEntity<Prescription>(prescription, HttpStatus.ACCEPTED);
	}
	
	@Override
	@PostMapping("save")
	public ResponseEntity<?> add (@RequestBody Prescription prescription){
		Prescription savedPrescription = prescriptionService.save(prescription);
		return new ResponseEntity<Prescription>(savedPrescription, HttpStatus.ACCEPTED);
	}
	
	@Override
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Prescription prescription = prescriptionService.findById(id);
		prescriptionService.delete(prescription);
		return new ResponseEntity<String>("Prescription deleted", HttpStatus.ACCEPTED);
	}
	
}
