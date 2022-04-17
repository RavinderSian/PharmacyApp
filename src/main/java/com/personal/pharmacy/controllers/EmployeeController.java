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

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("employee/")
public class EmployeeController implements CrudController<Employee, Long>{
	
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService employeeService) {
		this.service = employeeService;
	}

	@Override
	public ResponseEntity<?> getById(Long id){
		
		Optional<Employee> employeeOptional = service.findById(id);
		
		return employeeOptional.isEmpty()		
		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		
		if (service.delete(id) == 0) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> add(Employee employee, BindingResult bindingResult){
		if (bindingResult.hasFieldErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		service.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateEmployeeFirstName(@PathVariable Long id, @RequestBody String firstName){
		if (service.updateFirstName(id, firstName) == 0) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("{id}/prescriptions")
	public ResponseEntity<?> getPrescriptions(@PathVariable Long id){
		
		List<Prescription> prescriptions = service.findPrescriptionsForEmployee(id);
		
		if(prescriptions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(prescriptions, HttpStatus.OK);
	}
	
}
