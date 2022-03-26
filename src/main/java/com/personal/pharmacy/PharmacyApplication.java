package com.personal.pharmacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PharmacyApplication implements CommandLineRunner{

	@Autowired
	private EmployeeRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
		
	}
	
	public void test() {
		log.info(repository.findById(10L).toString());
		Employee employee = new Employee();
		employee.setFirstName("test3");
		employee.setLastName("testingdd");
		
		 
		log.info(repository.save(employee).getEmployeeId().toString());
	}

	@Override
	public void run(String... args) throws Exception {
		test();
		
	}

}
