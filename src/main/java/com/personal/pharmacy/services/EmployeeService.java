package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Employee;

public interface EmployeeService extends CrudService<Employee, Long>{

	Employee updateFirstName(Employee employee, String firstName);
	
}
