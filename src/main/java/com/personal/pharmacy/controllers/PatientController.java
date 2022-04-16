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

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("patient/")
public class PatientController implements CrudController<Patient, Long>{

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	private final PatientService patientService;
	
	@Override
	public ResponseEntity<?> getById(Long id){
		
		//more efficient to only call service once as this means the DB is only called once
		Optional<Patient> patientOptional = patientService.findById(id);
		
		return patientOptional.isEmpty()
		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> add(Patient patient, BindingResult bindingResult){
		
		if (bindingResult.hasFieldErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		patientService.save(patient);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> deleteById(Long id){
		Optional<Patient> patientOptional = patientService.findById(id);
		if (patientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		patientService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateFirstNameById(@PathVariable Long id, @RequestBody String firstName){
		
		if (patientService.updateFirstName(id, firstName) == 0) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("{id}/prescriptions")
	public ResponseEntity<?> getPrescriptions(@PathVariable Long id){
		
		List<Prescription> prescriptions = patientService.findPrescriptionsForPatient(id);
		
		if(prescriptions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(patientService.findPrescriptionsForPatient(id), HttpStatus.OK);
	}
	
}
