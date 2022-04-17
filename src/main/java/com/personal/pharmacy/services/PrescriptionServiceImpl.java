package com.personal.pharmacy.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.PrescriptionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService{

	private final PrescriptionRepository repository;
	
	@Override
	public Prescription save(Prescription prescription) {
		return repository.save(prescription);
	}

	@Override
	public Integer delete(Long id) {
		return repository.deleteById(id);
	}

	@Override
	public Optional<Prescription> findById(Long id) {
		return repository.findById(id).isEmpty()
		? Optional.empty()
		: repository.findById(id);
	}

	@Override
	public Integer addMedicine(Long prescriptionId, Long medicineId) {
		return repository.addMedicineToPrescription(prescriptionId, medicineId);
	}

}
