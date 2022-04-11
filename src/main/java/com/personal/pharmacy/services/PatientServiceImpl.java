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

}
