package com.personal.pharmacy.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.personal.pharmacy.mappers.EmployeeRowMapper;
import com.personal.pharmacy.model.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Employee employee) {
		
		jdbcTemplate.update(
	      "INSERT INTO employees (first_name, last_name) VALUES (?, ?)", 
	      employee.getFirstName(), employee.getLastName());
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		try {
			return 	Optional.of(jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id=?", new EmployeeRowMapper(), id));
		}catch(EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}
	
}
