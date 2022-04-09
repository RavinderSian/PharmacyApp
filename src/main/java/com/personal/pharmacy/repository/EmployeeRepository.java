package com.personal.pharmacy.repository;

import com.personal.pharmacy.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Employee updateFirstName(Employee employee, String firstName);

}
