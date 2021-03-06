package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Patient;

public interface PatientService extends CrudService<Patient, Long> {

	Patient updateFirstName(Patient patient, String firstName);
}
