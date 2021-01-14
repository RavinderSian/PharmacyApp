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

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.services.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("employee/")
public class EmployeeController implements CrudController<Employee, Long>{

	
	private final EmployeeService employeeService;

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){

		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (employeeOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employeeOptional.get(), HttpStatus.ACCEPTED);
	}
	
	@Override
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (employeeOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		employeeService.delete(employeeOptional.get());
		return new ResponseEntity<String>("Employee deleted", HttpStatus.ACCEPTED);
	}
	
	@Override
	@PostMapping("save")
	public ResponseEntity<?> add(@RequestBody Employee employee){
		Employee savedEmployee = employeeService.save(employee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);
	}
	
	@PostMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateEmployeeFirstName(@PathVariable Long id, @RequestBody String firstName){
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (employeeOptional.isEmpty()) {
			log.info("Id not present in database");
			return new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND);
		}
		Employee employee = employeeOptional.get();
		employeeService.updateFirstName(employee, firstName);
		return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}
	
}
