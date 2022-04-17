package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.model.Medicine;

public interface MedicineService extends CrudService<Medicine, Long>{

	Optional<Medicine> findByName(String name);
	Integer updateName(Long id, String name);
	List<Ingredient> findIngredientsByMedicine(Long id);
	List<Medicine> getMedicinesForPrescription(Long id);
	
}
