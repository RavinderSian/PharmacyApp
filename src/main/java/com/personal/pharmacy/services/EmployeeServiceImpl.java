package com.personal.pharmacy.services;

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
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id).isEmpty()
		?  Optional.empty()
		:  employeeRepository.findById(id);
	}

	@Override
	public Employee updateFirstName(Employee employee, String firstName) {
		employee.setFirstName(firstName);
		return employeeRepository.save(employee);
	}

}
 