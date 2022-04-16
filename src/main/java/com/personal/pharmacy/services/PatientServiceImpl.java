package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.PatientRepository;
import com.personal.pharmacy.repository.PrescriptionRepository;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository repository;
	
	private final PrescriptionRepository prescriptionRepository;
	
	public PatientServiceImpl(PatientRepository repository, PrescriptionRepository prescriptionRepository) {
		this.repository = repository;
		this.prescriptionRepository = prescriptionRepository;
	}

	@Override
	public Patient save(Patient patient) {
		return repository.save(patient);
	}

	@Override
	public Integer delete(Long id) {
		return repository.deleteById(id);
	}

	@Override
	public Optional<Patient> findById(Long id) {
		
		return repository.findById(id).isEmpty()
		? Optional.empty()
		: repository.findById(id);
	}

	@Override
	public Integer updateFirstName(Long id, String firstName) {
		return repository.updateFirstName(id, firstName);
	}
	
	public List<Prescription> findPrescriptionsForPatient(Long id){
		return prescriptionRepository.findPrescriptionsForPatient(id);
	}

}
