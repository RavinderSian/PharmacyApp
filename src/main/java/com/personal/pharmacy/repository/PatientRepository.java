package com.personal.pharmacy.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.pharmacy.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long>{

}
