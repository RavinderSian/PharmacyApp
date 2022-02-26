package com.personal.pharmacy.repository;


import com.personal.pharmacy.model.Medicine;

public interface MedicineRepository {

	Medicine findByName(String name);

}

