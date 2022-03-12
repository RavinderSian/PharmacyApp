package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void delete(Long id) {
		//employee.getPrescriptions().forEach(prescription -> prescription.setEmployee(null));
		employeeRepository.deleteById(id);
	}

	
	@Override
	public List<Employee> findAll() {
		//return (List<Employee>) employeeRepository.findAll();
		return null;
	}
	
	
	@Override
	public Optional<Employee> findById(Long id) {
		
//		return employeeRepository.findById(id).isEmpty()
//		?  Optional.empty()
//		:  employeeRepository.findById(id);
		return null;
	}

	@Override
	public Employee updateFirstName(Employee employee, String firstName) {
//		employee.setFirstName(firstName);
//		return employeeRepository.save(employee);
		return null;
	}

}
 