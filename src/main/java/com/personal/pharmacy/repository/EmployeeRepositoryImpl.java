package com.personal.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.personal.pharmacy.mappers.EmployeeRowMapper;
import com.personal.pharmacy.model.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Employee save(Employee employee) {
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO employees (first_name, last_name, creation_timestamp, updated_timestamp) values(?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, employee.getFirstName());
				ps.setString(2, employee.getLastName());
				return ps;
			}
		}, holder);
		
		Number newUserId = (Long) holder.getKeys().get("id");
		
		employee.setEmployeeId(newUserId.longValue());
		return employee;
	}

	@Override
	public Integer deleteById(Long id) {
		return jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		try {
			return 	Optional.of(jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id=?", new EmployeeRowMapper(), id));
		}catch(EmptyResultDataAccessException exception) {
			return Optional.empty();
		}
	}
	
	@Override
	public Integer updateFirstName(Long id, String firstName) {
		
		return jdbcTemplate.update("UPDATE employees SET first_name='" + firstName
				+ "', updated_timestamp = CURRENT_TIMESTAMP WHERE id =" + id);
	}
	
}
