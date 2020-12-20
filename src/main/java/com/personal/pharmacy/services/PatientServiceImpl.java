package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.repository.PatientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	
	@Override
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void delete(Patient patient) {
		patientRepository.delete(patient);
	}

	@Override
	public List<Patient> findAll() {
		return (List<Patient>) patientRepository.findAll();
	}

	@Override
	public Patient findById(Long id) {
		
		Optional<Patient> patientOptional = patientRepository.findById(id);
		if (!patientOptional.isPresent()) {
			throw new RuntimeException("No medicine with given id");
		}
		return patientOptional.get();
	}

	@Override
	public Patient updateFirstName(Patient patient, String firstName) {
		patient.setFirstName(firstName);
		return patient;
	}

}
