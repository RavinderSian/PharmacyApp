package com.personal.pharmacy.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.PrescriptionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService{

	private final PrescriptionRepository prescriptionRepository;
	
	@Override
	public Prescription save(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}

	@Override
	public Integer delete(Long id) {
		return prescriptionRepository.deleteById(id);
	}

	@Override
	public Optional<Prescription> findById(Long id) {
		return prescriptionRepository.findById(id).isEmpty()
		? Optional.empty()
		: prescriptionRepository.findById(id);
	}

}
