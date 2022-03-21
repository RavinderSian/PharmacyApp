package com.personal.pharmacy.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.personal.pharmacy.mappers.EmployeeRowMapper;
import com.personal.pharmacy.model.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

	@Override
	public Employee save(Employee employee) {
		
		simpleJdbcInsert.withTableName("employees").usingGeneratedKeyColumns("id");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("first_name", employee.getFirstName());
		params.addValue("last_name", employee.getLastName());
		
		long savedId = simpleJdbcInsert.executeAndReturnKey(params).longValue();
		
		return findById(savedId).get();
		
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
