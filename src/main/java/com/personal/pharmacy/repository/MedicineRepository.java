package com.personal.pharmacy.repository;


import java.util.Optional;

import com.personal.pharmacy.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {

	Optional<Medicine> findByName(String name);

	Integer updateName(Long id, String name);

}

