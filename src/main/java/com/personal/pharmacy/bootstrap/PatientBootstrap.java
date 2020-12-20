package com.personal.pharmacy.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.repository.PatientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PatientBootstrap implements CommandLineRunner {

	private final PatientRepository patientRepository;

	@Override
	public void run(String... args) throws Exception {

		Patient patient = new Patient();
		patient.setFirstName("test name");
		patientRepository.save(patient);
		
	}
	
	
	
}
