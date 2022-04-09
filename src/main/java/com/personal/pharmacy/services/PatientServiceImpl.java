package com.personal.pharmacy.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.repository.PatientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository repository;
	
	@Override
	public Patient save(Patient patient) {
		return repository.save(patient);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Patient> findById(Long id) {
		
		return repository.findById(id).isEmpty()
		? Optional.empty()
		: repository.findById(id);
	}

	@Override
	public Patient updateFirstName(Patient patient, String firstName) {
		patient.setFirstName(firstName);
		return repository.save(patient);
	}

}
