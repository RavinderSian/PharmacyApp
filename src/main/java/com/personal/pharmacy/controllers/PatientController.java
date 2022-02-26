package com.personal.pharmacy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("patient/")
public class PatientController {

//	public PatientController(PatientService patientService) {
//		this.patientService = patientService;
//	}
//
//	private final PatientService patientService;
//	
//	@Override
//	public ResponseEntity<?> getById(Long id){
//		return patientService.findById(id).isEmpty()
//		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//		: new ResponseEntity<>(patientService.findById(id).get(), HttpStatus.OK);
//	}
//	
//	@Override
//	public ResponseEntity<?> add(Patient patient, BindingResult bindingResult){
//		
//		if (bindingResult.hasFieldErrors()) {
//			Map<String, String> errors = new HashMap<>();
//			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//		}
//		patientService.save(patient);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@Override
//	public ResponseEntity<?> deleteById(Long id){
//		Optional<Patient> patientOptional = patientService.findById(id);
//		if (patientOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		patientService.delete(patientOptional.get());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@PatchMapping("{id}/updatefirstname")
//	public ResponseEntity<?> updateFirstNameById(@PathVariable Long id, @RequestBody String firstName){
//		Optional<Patient> patientOptional = patientService.findById(id);
//		if (patientOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		Patient patient = patientOptional.get();
//		patientService.updateFirstName(patient, firstName);
//		return new ResponseEntity<>(patient, HttpStatus.OK);
//	}
	
}
