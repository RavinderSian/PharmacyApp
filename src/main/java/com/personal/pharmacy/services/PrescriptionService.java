package com.personal.pharmacy.services;

import com.personal.pharmacy.model.Prescription;

public interface PrescriptionService extends CrudService<Prescription, Long> {
	
	Integer addMedicine(Long prescriptionId, Long medicineId);
}
