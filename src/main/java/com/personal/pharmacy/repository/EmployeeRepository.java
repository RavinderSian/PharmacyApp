package com.personal.pharmacy.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.pharmacy.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	
}
