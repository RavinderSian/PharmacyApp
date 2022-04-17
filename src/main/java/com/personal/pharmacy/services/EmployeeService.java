package com.personal.pharmacy.services;

import java.util.List;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.model.Prescription;

public interface EmployeeService extends CrudService<Employee, Long>{
	
	Integer updateFirstName(Long id, String firstName);
	List<Prescription> findPrescriptionsForEmployee(Long id);

	
}
