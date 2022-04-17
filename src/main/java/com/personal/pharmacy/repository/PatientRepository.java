package com.personal.pharmacy.repository;

import com.personal.pharmacy.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

	Integer updateFirstName(Long id, String firstName);

}
