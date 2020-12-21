package com.personal.pharmacy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.services.PatientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("patient/")
public class PatientController implements CrudController<Patient, Long> {

	private final PatientService patientService;
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Patient patient = patientService.findById(id);
		return new ResponseEntity<Patient>(patient, HttpStatus.ACCEPTED);
	}
	
	@Override
	@PostMapping("save")
	public ResponseEntity<?> add(@RequestBody Patient patient){
		Patient savedPatient = patientService.save(patient);
		return new ResponseEntity<Patient>(savedPatient, HttpStatus.ACCEPTED);
	}
	
	@Override
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Patient patient = patientService.findById(id);
		patientService.delete(patient);
		return new ResponseEntity<String>("Patient deleted", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateFirstNameById(@PathVariable Long id, @RequestBody String firstName){
		Patient patient = patientService.findById(id);
		patientService.updateFirstName(patient, firstName);
		Patient updatedPatient = patientService.save(patient);
		return new ResponseEntity<Patient>(updatedPatient, HttpStatus.ACCEPTED);
	}
	
}
