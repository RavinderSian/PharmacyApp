package com.personal.pharmacy.services;

import java.util.Optional;

import com.personal.pharmacy.model.Medicine;

public interface MedicineService extends CrudService<Medicine, Long>{

	Optional<Medicine> findByName(String name);
	Integer updateName(Long id, String name);
}
