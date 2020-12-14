package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.repository.MedicineRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService{

	private final MedicineRepository medicineRepository;
	
	@Override
	public Medicine save(Medicine medicine) {
		return medicineRepository.save(medicine);
 	}

	@Override
	public void delete(Medicine medicine) {
		medicineRepository.delete(medicine);
		
	}

	@Override
	public List<Medicine> findAll() {
		return (List<Medicine>) medicineRepository.findAll();
	}

	@Override
	public Medicine findById(Long id) {
		
		Optional<Medicine> medicineOptional = medicineRepository.findById(id);
		if (!medicineOptional.isPresent()) {
			throw new RuntimeException("No medicine with given id");
		}
		return medicineOptional.get();
	}

	@Override
	public Medicine findByName(String name) {
		return medicineRepository.findByName(name);
	}

}
