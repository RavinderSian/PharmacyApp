package com.personal.pharmacy.repository;

import java.util.Optional;

import com.personal.pharmacy.model.Employee;

public interface EmployeeRepository {

	Employee save(Employee employee);
	void deleteById(Long id);
	Optional<Employee> findById(Long id);
}
