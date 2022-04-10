package com.personal.pharmacy.repository;

import com.personal.pharmacy.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Integer updateFirstName(Long id, String firstName);

}
