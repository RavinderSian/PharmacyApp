package com.personal.pharmacy.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("employee/")
public class EmployeeController implements CrudController<Employee, Long>{
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public ResponseEntity<?> getById(Long id){
		return employeeService.findById(id).isEmpty()		
		? new ResponseEntity<>(HttpStatus.NOT_FOUND)
		: new ResponseEntity<>(employeeService.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		
		if (employeeService.delete(id) != 1) {
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
		employeeService.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateEmployeeFirstName(@PathVariable Long id, @RequestBody String firstName){
		if (employeeService.updateFirstName(id, firstName) != 1) {
			log.info("Id not present in database");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		employeeService.updateFirstName(id, firstName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
