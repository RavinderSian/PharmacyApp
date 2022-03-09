package com.personal.pharmacy.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.personal.pharmacy.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Employee employee) {
		
		jdbcTemplate.update(
			      "INSERT INTO employees (first_name, last_name) VALUES (?, ?)", 
			      employee.getFirstName(), employee.getLastName());
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
