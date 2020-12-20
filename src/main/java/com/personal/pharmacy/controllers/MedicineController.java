package com.personal.pharmacy.controllers;

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

@AllArgsConstructor
@RequestMapping("medicine/")
@RestController
public class MedicineController {
	
	private final MedicineService medicineService;

	@GetMapping("{id}")
	public ResponseEntity<?> getMedicineById(@PathVariable Long id){
		Medicine medicine = medicineService.findById(id);
		return new ResponseEntity<Medicine>(medicine, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteMedicineById(@PathVariable Long id){
		Medicine medicine = medicineService.findById(id);
		medicineService.delete(medicine);
		return new ResponseEntity<String>("Medicine deleted", HttpStatus.ACCEPTED);
	}
	 
	@PostMapping("save")
	public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine){
		Medicine savedMedicine = medicineService.save(medicine);
		return new ResponseEntity<Medicine>(savedMedicine, HttpStatus.OK);
	}
	
	@PostMapping("{id}/updatename")
	public ResponseEntity<?> updateMedicineName(@PathVariable Long id, @RequestBody String name){
		Medicine medicine = medicineService.findById(id);
		medicineService.updateName(medicine, name);
		medicineService.save(medicine);
		return new ResponseEntity<Medicine>(medicine, HttpStatus.ACCEPTED);
	}
	
}
