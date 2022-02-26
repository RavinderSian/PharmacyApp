package com.personal.pharmacy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("prescription/")
public class PrescriptionController {

//	private final PrescriptionService prescriptionService;
//	
//	public PrescriptionController(PrescriptionService prescriptionService) {
//		this.prescriptionService = prescriptionService;
//	}
//
//	@Override
//	@GetMapping("{id}")
//	public ResponseEntity<?> getById(Long id){
//		return prescriptionService.findById(id).isEmpty()
//		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//		: new ResponseEntity<>(prescriptionService.findById(id).get(), HttpStatus.OK);
//	}
//	
//	@Override
//	public ResponseEntity<?> add (Prescription prescription, BindingResult bindingResult){
//		if (bindingResult.hasFieldErrors()) {
//			Map<String, String> errors = new HashMap<>();
//			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//		}
//		prescriptionService.save(prescription);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@Override
//	public ResponseEntity<?> deleteById(Long id){
//		Optional<Prescription> prescriptionOptional = prescriptionService.findById(id);
//		if (prescriptionOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		prescriptionService.delete(prescriptionOptional.get());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
}
