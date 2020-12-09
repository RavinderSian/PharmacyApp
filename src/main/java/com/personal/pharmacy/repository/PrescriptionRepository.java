package com.personal.pharmacy.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.pharmacy.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

}
