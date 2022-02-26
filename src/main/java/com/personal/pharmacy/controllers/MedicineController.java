package com.personal.pharmacy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("medicine/")
public class MedicineController {
	
//	private final MedicineService medicineService;
//	
//	public MedicineController(MedicineService medicineService) {
//		this.medicineService = medicineService;
//	}
//
//	@Override
//	public ResponseEntity<?> getById(Long id){
//		return medicineService.findById(id).isEmpty()
//		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//		: new ResponseEntity<>(medicineService.findById(id).get(), HttpStatus.OK);
//	}
//	
//	@Override
//	public ResponseEntity<?> deleteById(Long id){
//		Optional<Medicine> medicineOptional = medicineService.findById(id);
//		if (medicineOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		medicineService.delete(medicineOptional.get());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	 
//	@Override
//	public ResponseEntity<?> add(Medicine medicine, BindingResult bindingResult){
//		if (bindingResult.hasFieldErrors()) {
//			Map<String, String> errors = new HashMap<>();
//			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//		}
//		medicineService.save(medicine);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//	@PatchMapping("{id}/updatename")
//	public ResponseEntity<?> updateMedicineName(@PathVariable Long id, @RequestBody String name){
//		Optional<Medicine> medicineOptional = medicineService.findById(id);
//		if (medicineOptional.isEmpty()) {
//			log.info("Id not present in database");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		Medicine medicine = medicineOptional.get();
//		medicineService.updateName(medicine, name);
//		return new ResponseEntity<>(medicine, HttpStatus.OK);
//	}
	
}
