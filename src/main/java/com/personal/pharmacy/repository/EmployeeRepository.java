package com.personal.pharmacy.repository;

import com.personal.pharmacy.model.Employee;

public interface EmployeeRepository {

	void save(Employee employee);
	void deleteById(Long id);
}
