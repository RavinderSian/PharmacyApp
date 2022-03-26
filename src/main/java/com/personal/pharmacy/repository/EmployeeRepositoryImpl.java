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
				PreparedStatement ps = connection.prepareStatement("INSERT INTO EMPLOYEES (first_name, last_name) values(?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, employee.getFirstName());
				ps.setString(2, employee.getLastName());
				return ps;
			}
		}, holder);
		
		Number newUserId = (Integer) holder.getKeys().get("id");
		
		employee.setEmployeeId(newUserId.longValue());
		return employee;
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
