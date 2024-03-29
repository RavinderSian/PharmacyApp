package com.personal.pharmacy.repository;

import java.util.List;

import com.personal.pharmacy.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

	List<Prescription> findPrescriptionsForPatient(Long id);
	List<Prescription> findPrescriptionsForEmployee(Long id);
	Integer addMedicineToPrescription(Long prescriptionId, Long medicineId);
	List<Long> getIdsOfMedicineInPrescription(Long id);
	
}
