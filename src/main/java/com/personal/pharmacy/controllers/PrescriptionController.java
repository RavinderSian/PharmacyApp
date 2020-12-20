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
public class PrescriptionController {

	private final PrescriptionService prescriptionService;
	
	@GetMapping("{id}")
	public ResponseEntity<?> getPrescriptionById(@PathVariable Long id){
		Prescription prescription = prescriptionService.findById(id);
		return new ResponseEntity<Prescription>(prescription, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("save")
	public ResponseEntity<?> addPrescription (@RequestBody Prescription prescription){
		Prescription savedPrescription = prescriptionService.save(prescription);
		return new ResponseEntity<Prescription>(savedPrescription, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deletePrescriptionById(@PathVariable Long id){
		Prescription prescription = prescriptionService.findById(id);
		prescriptionService.delete(prescription);
		return new ResponseEntity<String>("Prescription deleted", HttpStatus.ACCEPTED);
	}
	
}
