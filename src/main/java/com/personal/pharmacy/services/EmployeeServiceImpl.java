package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.exceptions.NoDataForIdException;
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
	public void delete(Employee employee) {
		employeeRepository.delete(employee);

	}

	
	@Override
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	
	@Override
	public Employee findById(Long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (!employeeOptional.isPresent()) {
			throw new NoDataForIdException("No employee with id " + id);
		}
		return employeeOptional.get();
	}

	@Override
	public Employee updateFirstName(Employee employee, String firstName) {
		employee.setFirstName(firstName);
		return employeeRepository.save(employee);
	}

}
 