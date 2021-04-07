package com.personal.pharmacy.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	public ResponseEntity<?> getById(Long id){

		return employeeService.findById(id).isEmpty()		
		? new ResponseEntity<String>("No data found for id " + id, HttpStatus.NOT_FOUND)
		: new ResponseEntity<Employee>(employeeService.findById(id).get(), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> deleteById(Long aLong) {
		return null;
	}

	public String deletingById(@PathVariable Long id){
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (employeeOptional.isEmpty()) {
			log.info("Id not present in database");
			return "No data found for id " + id;
		}
		employeeService.delete(employeeOptional.get());
		return "Employee deleted";
	}
	
	@Override
	public ResponseEntity<?> add(Employee employee, BindingResult bindingResult){
		
		if (bindingResult.hasFieldErrors()) {
			
			List<String> errorStrings = new ArrayList<>();
			bindingResult.getFieldErrors().forEach(objectError -> {
				errorStrings.add(objectError.getDefaultMessage());
				
			});
			return new ResponseEntity<String>(errorStrings.toString(), HttpStatus.BAD_REQUEST);
		}
		
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
