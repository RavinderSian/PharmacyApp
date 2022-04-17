package com.personal.pharmacy.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.model.Prescription;

@AutoConfigureTestDatabase
@SpringBootTest
class PrescriptionRepositoryImplTest {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Autowired
	PrescriptionRepository repository;
    
    @Autowired
    PatientRepository patientRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    MedicineRepository medicineRepository;
    
    @BeforeEach
    void createTable() {
    	
    	jdbcTemplate.execute("CREATE TABLE medicine ( id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "name varchar(50) NOT NULL, dosage int, duration varchar(50), creation_timestamp DATETIME, "
    			+ "updated_timestamp DATETIME)");
    	
    	jdbcTemplate.execute("CREATE TABLE employees ( ID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "FIRST_NAME varchar(50) NOT NULL, LAST_NAME varchar(50) NOT NULL, "
    			+ "CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME)");
    	
    	//create patient table before hand as it is referenced later
    	jdbcTemplate.execute("CREATE TABLE patient ( ID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "FIRST_NAME varchar(50) NOT NULL, LAST_NAME varchar(50) NOT NULL, "
    			+ "CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME);");
    	
    	jdbcTemplate.execute("CREATE TABLE prescription ( ID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "CREATION_TIMESTAMP DATETIME, UPDATED_TIMESTAMP DATETIME, "
    			+ "patient_id bigint REFERENCES patient(id), employee_id bigint REFERENCES employees(id));");
    	
    	jdbcTemplate.execute("CREATE TABLE prescription_medicine ( prescription_id bigint NOT NULL "
    			+ "AUTO_INCREMENT, CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME, medicine_id bigint);");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS prescription");
    	jdbcTemplate.execute("DROP TABLE IF EXISTS patient");
    	jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
    	jdbcTemplate.execute("DROP TABLE IF EXISTS medicine");
    	jdbcTemplate.execute("DROP TABLE IF EXISTS prescription_medicine");

    }
 
	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));
	}

	@Test
	void test_Save_ReturnsCorrectPrescription_WhenGivenMockPrescription() {
		
		Prescription prescription = new Prescription();
		repository.save(prescription);
		
		assertThat(repository.findById(1L).get().getPrescriptionId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Prescription prescription = new Prescription();
		repository.save(prescription);
		assertThat(repository.findById(1L), not(equalTo(Optional.empty())));
		
		repository.deleteById(1L);
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsCorrectPrescription_WhenIdPresentInDatabase() {
		
		Prescription prescription = new Prescription();
		repository.save(prescription);
		
		Optional<Prescription> prescriptionInDb = repository.findById(1L);
		
		assertThat(prescriptionInDb.get().getPrescriptionId(), equalTo(1L));
		assertThat(prescriptionInDb.get().getCreatedTime(), is(notNullValue()));
		assertThat(prescriptionInDb.get().getUpdatedTime(), is(notNullValue()));
		
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenIdNotPresentInDatabase() {
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindPrescriptionsForPatient_ReturnsListOfPrescriptions_WhenPrescriptionsExistForPatient() {
		 
		Patient patient = new Patient();
		patient.setFirstName("test");
		patient.setLastName("testing");
		
		patientRepository.save(patient);
		
		Prescription prescription = new Prescription();
		prescription.setPatientId(1L);
		repository.save(prescription);
		
		Prescription secondPrescription = new Prescription();
		secondPrescription.setPatientId(1L);
		repository.save(prescription);
		
		assertThat(repository.findPrescriptionsForPatient(1L).size(), equalTo(2));
		
	}
	
	@Test
	void test_FindPrescriptionsForPatient_ReturnsEmptyList_WhenPrescriptionsDoNotExistForPatient() {
		 
		Patient patient = new Patient();
		patient.setFirstName("test");
		patient.setLastName("testing");
		
		patientRepository.save(patient);
		
		assertThat(repository.findPrescriptionsForPatient(1L).size(), equalTo(0));
		
	}
	
	
	@Test
	void test_FindPrescriptionsForEmployee_ReturnsListOfPrescriptions_WhenPrescriptionsExistForEmployee() {
		 
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		
		employeeRepository.save(employee);
		
		Prescription prescription = new Prescription();
		prescription.setEmployeeId(1L);
		repository.save(prescription);
		
		Prescription secondPrescription = new Prescription();
		secondPrescription.setEmployeeId(1L);
		repository.save(secondPrescription);
		
		assertThat(repository.findPrescriptionsForEmployee(1L).size(), equalTo(2));
		
	}
	
	@Test
	void test_FindPrescriptionsForEmployee_ReturnsEmptyList_WhenPrescriptionsDoNotExistForEmployee() {
		 
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		
		employeeRepository.save(employee);
		
		assertThat(repository.findPrescriptionsForEmployee(1L).size(), equalTo(0));
		
	}
	
	@Test
	void test_FindPrescriptionsForEmployee_ReturnsEmptyList_WhenEmployeeDosentExist() {
		
		assertThat(repository.findPrescriptionsForEmployee(1L).size(), equalTo(0));
		
	}
	
	@Test
	void test_AddMedicine_ReturnsCorrectResponse_WhenGivenIds() {
		assertThat(repository.addMedicineToPrescription(1L, 1L), equalTo(1));
	}
	
	@Test
	void test_GetIdsOfMedicineInPrescription_ReturnsCorrectList_WhenMedicinesPresentWithPrescriptionId() {
		
		Prescription prescription = new Prescription();
		repository.save(prescription);
		
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		medicineRepository.save(medicine);
		
		Medicine secondMedicine = new Medicine();
		secondMedicine.setName("test");
		secondMedicine.setDosage(1);
		secondMedicine.setDuration("1 day");
		medicineRepository.save(secondMedicine);
		
		repository.addMedicineToPrescription(1L, 1L);
		repository.addMedicineToPrescription(1L, 2L);
		
		List<Long> medicineIds = repository.getIdsOfMedicineInPrescription(1L);
		
		assertThat(medicineIds.size(), equalTo(2));
		assertThat(medicineIds.get(0), equalTo(1L));
		assertThat(medicineIds.get(1), equalTo(2L));

	}
	
	@Test
	void test_GetIdsOfMedicineInPrescription_ReturnsEmptyList_WhenMedicinesNotPresentWithPrescriptionId() {
		
		Prescription prescription = new Prescription();
		repository.save(prescription);
		
		List<Long> medicineIds = repository.getIdsOfMedicineInPrescription(1L);
		
		assertThat(medicineIds.size(), equalTo(0));

	}
	
	@Test
	void test_GetIdsOfMedicineInPrescription_ReturnsEmptyList_WhenPrescriptionIdNotPresent() {
		
		List<Long> medicineIds = repository.getIdsOfMedicineInPrescription(1L);
		
		assertThat(medicineIds.size(), equalTo(0));

	}

}
