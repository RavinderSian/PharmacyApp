package com.personal.pharmacy.services;

import java.util.List;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.model.Prescription;

public interface PatientService extends CrudService<Patient, Long> {

	Integer updateFirstName(Long id, String firstName);
	List<Prescription> findPrescriptionsForPatient(Long id);
	
}
