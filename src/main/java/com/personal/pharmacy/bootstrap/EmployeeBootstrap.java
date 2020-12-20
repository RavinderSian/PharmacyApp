package com.personal.pharmacy.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmployeeBootstrap implements CommandLineRunner{
	
	private final EmployeeRepository employeeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		 Employee employee = new Employee();
		 employee.setFirstName("new name");
		 employeeRepository.save(employee);
	}

	
}
