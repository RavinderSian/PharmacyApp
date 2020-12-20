package com.personal.pharmacy.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.PrescriptionRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PrescriptionBootStrap implements CommandLineRunner {
	
	private final PrescriptionRepository prescriptionRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Prescription prescription = new Prescription();
		prescriptionRepository.save(prescription);
		
	}

}
