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
	public ResponseEntity<?> getById(Long id){
		return patientService.findById(id).isEmpty()
		? new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND)
		: new ResponseEntity<Patient>(patientService.findById(id).get(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> add(Patient patient, BindingResult bindingResult){
		
		if (bindingResult.hasFieldErrors()) {
			List<String> errorStrings = new ArrayList<>();
			bindingResult.getFieldErrors().forEach(objectError -> {
				errorStrings.add(objectError.getDefaultMessage());
				
			});
			return new ResponseEntity<String>(errorStrings.toString(), HttpStatus.BAD_REQUEST);
		}
		
		Patient savedPatient = patientService.save(patient);
		return new ResponseEntity<Patient>(savedPatient, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> deleteById(Long id){
		Optional<Patient> patientOptional = patientService.findById(id);
		if (patientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		patientService.delete(patientOptional.get());
		return new ResponseEntity<String>("Patient deleted", HttpStatus.OK);
	}
	
	@PatchMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateFirstNameById(@PathVariable Long id, @RequestBody String firstName){
		Optional<Patient> patientOptional = patientService.findById(id);
		if (patientOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		Patient patient = patientOptional.get();
		patientService.updateFirstName(patient, firstName);
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
	
}
