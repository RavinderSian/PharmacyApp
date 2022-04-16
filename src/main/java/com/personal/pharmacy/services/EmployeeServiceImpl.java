package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.EmployeeRepository;
import com.personal.pharmacy.repository.PrescriptionRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	private final PrescriptionRepository prescriptionRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, PrescriptionRepository prescriptionRepository) {
		this.employeeRepository = employeeRepository;
		this.prescriptionRepository = prescriptionRepository;
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Integer delete(Long id) {
		return employeeRepository.deleteById(id);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id).isEmpty()
		?  Optional.empty()
		:  employeeRepository.findById(id);
	}

	@Override
	public Integer updateFirstName(Long id, String firstName) {
		
		return employeeRepository.updateFirstName(id, firstName);
	}

	@Override
	public List<Prescription> findPrescriptionsForEmployee(Long id) {
		return prescriptionRepository.findPrescriptionsForEmployee(id);
	}

}
 