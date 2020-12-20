package com.personal.pharmacy.controllers;

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

@AllArgsConstructor
@RequestMapping("employee/")
@RestController
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeService.findById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id){
		Employee employee = employeeService.findById(id);
		employeeService.delete(employee);
		return new ResponseEntity<String>("Employee deleted", HttpStatus.ACCEPTED);
	}
	 
	@PostMapping("save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		Employee savedEmployee = employeeService.save(employee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}
	
	@PostMapping("{id}/updatefirstname")
	public ResponseEntity<?> updateEmployeeFirstName(@PathVariable Long id, @RequestBody String firstName){
		Employee employee = employeeService.findById(id);
		employeeService.updateFirstName(employee, firstName);
		employeeService.save(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}
	
}
