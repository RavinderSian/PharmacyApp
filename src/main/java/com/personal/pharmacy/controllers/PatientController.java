package com.personal.pharmacy.controllers;

import java.util.Optional;

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
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("patient/")
public class PatientController implements CrudController<Patient, Long> {

	private final PatientService patientService;
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<Patient> patientOptional = patientService.findById(id);
		if (patientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patient>(patientOptional.get(), HttpStatus.ACCEPTED);
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
		Optional<Patient> patientOptional = patientService.findById(id);
		if (patientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		patientService.delete(patientOptional.get());
		return new ResponseEntity<String>("Patient deleted", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateFirstNameById(@PathVariable Long id, @RequestBody String firstName){
		Optional<Patient> patientOptional = patientService.findById(id);
		if (patientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		Patient patient = patientOptional.get();
		patientService.updateFirstName(patient, firstName);
		return new ResponseEntity<Patient>(patient, HttpStatus.ACCEPTED);
	}
	
}
