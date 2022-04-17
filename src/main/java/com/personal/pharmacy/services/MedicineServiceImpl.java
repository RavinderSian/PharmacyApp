package com.personal.pharmacy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.repository.IngredientRepository;
import com.personal.pharmacy.repository.MedicineRepository;
import com.personal.pharmacy.repository.PrescriptionRepository;

@Service
public class MedicineServiceImpl implements MedicineService {

	private final MedicineRepository medicineRepository;
	private final IngredientRepository ingredientRepository;
	private final PrescriptionRepository prescriptionRepository;
	
	public MedicineServiceImpl(MedicineRepository medicineRepository, IngredientRepository ingredientRepository,
								PrescriptionRepository prescriptionRepository) {
		this.medicineRepository = medicineRepository;
		this.ingredientRepository = ingredientRepository;
		this.prescriptionRepository = prescriptionRepository;
	}

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

	@Override
	public List<Ingredient> findIngredientsByMedicine(Long id) {
		return ingredientRepository.findIngredientsByMedicine(id);
	}

	@Override
	public List<Medicine> getMedicinesForPrescription(Long id) {

		List<Medicine> medicineList = new ArrayList<>();
		
		for (Long medicineId : prescriptionRepository.getIdsOfMedicineInPrescription(id)) {
			medicineList.add(findById(medicineId).get());
		}
		
		return medicineList;
	}

}
