package com.personal.pharmacy.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.repository.MedicineRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {

	private final MedicineRepository medicineRepository;
	
	@Override
	public Medicine save(Medicine medicine) {
		return medicineRepository.save(medicine);
 	}

	@Override
	public Integer delete(Long id) {
		return medicineRepository.deleteById(id);
		
	}

	@Override
	public Optional<Medicine> findById(Long id) {
		
		return medicineRepository.findById(id).isEmpty()
		? Optional.empty()
		: medicineRepository.findById(id);
	}

	@Override
	public Optional<Medicine> findByName(String name) {
		return medicineRepository.findByName(name);
	}

	@Override
	public Integer updateName(Long id, String name) {
		return medicineRepository.updateName(id, name);
	}

}
