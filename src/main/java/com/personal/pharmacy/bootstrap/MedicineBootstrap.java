package com.personal.pharmacy.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.repository.MedicineRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MedicineBootstrap implements CommandLineRunner {

	private final MedicineRepository medicineRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Medicine medicine = new Medicine();
		medicine.setName("Paracetamol");
		medicineRepository.save(medicine);
	}

}
