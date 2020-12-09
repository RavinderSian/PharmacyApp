package com.personal.pharmacy.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.pharmacy.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long>{

	Medicine findByName(String name);

}

