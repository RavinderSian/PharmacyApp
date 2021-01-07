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

import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.services.MedicineService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("medicine/")
public class MedicineController implements CrudController<Medicine, Long>{
	
	private final MedicineService medicineService;

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<Medicine> medicineOptional = medicineService.findById(id);
		if (medicineOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Medicine>(medicineOptional.get(), HttpStatus.ACCEPTED);
	}
	
	@Override
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Optional<Medicine> medicineOptional = medicineService.findById(id);
		if (medicineOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.ACCEPTED);
		}
		medicineService.delete(medicineOptional.get());
		return new ResponseEntity<String>("Medicine deleted", HttpStatus.ACCEPTED);
	}
	 
	@Override
	@PostMapping("save")
	public ResponseEntity<?> add(@RequestBody Medicine medicine){
		Medicine savedMedicine = medicineService.save(medicine);
		return new ResponseEntity<Medicine>(savedMedicine, HttpStatus.OK);
	}
	
	@PostMapping("{id}/updatename")
	public ResponseEntity<?> updateMedicineName(@PathVariable Long id, @RequestBody String name){
		Optional<Medicine> medicineOptional = medicineService.findById(id);
		if (medicineOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.ACCEPTED);
		}
		Medicine medicine = medicineOptional.get();
		medicineService.updateName(medicine, name);
		return new ResponseEntity<Medicine>(medicine, HttpStatus.ACCEPTED);
	}
	
}
