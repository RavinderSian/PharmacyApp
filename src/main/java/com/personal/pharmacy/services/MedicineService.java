package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Medicine;

public interface MedicineService extends CrudService<Medicine, Long>{

	Medicine findByName(String name);
}
