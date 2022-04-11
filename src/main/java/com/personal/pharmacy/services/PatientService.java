package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Patient;

public interface PatientService extends CrudService<Patient, Long> {

	Integer updateFirstName(Long id, String firstName);
}
