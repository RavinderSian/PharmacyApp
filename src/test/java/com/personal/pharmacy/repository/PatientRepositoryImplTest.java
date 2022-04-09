package com.personal.pharmacy.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.personal.pharmacy.model.Patient;

@SpringBootTest
@AutoConfigureTestDatabase
class PatientRepositoryImplTest {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Autowired
	PatientRepository repository;
    
    @BeforeEach
    void createTable() {
    	jdbcTemplate.execute("CREATE TABLE patient ( ID int NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "FIRST_NAME varchar(50) NOT NULL, LAST_NAME varchar(50) NOT NULL, "
    			+ "CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME);");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS patient");
    }

	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));
	}

	@Test
	void test_Save_ReturnsCorrectPatient_WhenGivenMockPatient() {
		
		Patient patient = new Patient();
		patient.setFirstName("test");
		patient.setLastName("testing");
		patient.setCreatedTime();
		patient.setUpdatedTime();
		
		Patient savedPatient = repository.save(patient);
		
		assertThat(patient.getFirstName(), equalTo(savedPatient.getFirstName()));
		assertThat(patient.getLastName(), equalTo(savedPatient.getLastName()));
		assertThat(repository.findById(1L).get().getPatientId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Patient patient = new Patient();
		patient.setFirstName("test");
		patient.setLastName("testing");
		patient.setCreatedTime();
		patient.setUpdatedTime();
		repository.save(patient);
		assertThat(repository.findById(1L), not(equalTo(Optional.empty())));
		
		repository.deleteById(1L);
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenIdNotPresentInDatabase() {
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsCorrectEmployee_WhenIdPresentInDatabase() {
		
		Patient patient = new Patient();
		patient.setFirstName("test");
		patient.setLastName("testing");
		patient.setCreatedTime();
		patient.setUpdatedTime();
		repository.save(patient);
		
		Optional<Patient> patientInDb = repository.findById(1L);
		
		assertThat(patientInDb.get().getPatientId(), equalTo(1L));
		assertThat(patientInDb.get().getFirstName(), equalTo("test"));
		assertThat(patientInDb.get().getLastName(), equalTo("testing"));
		assertThat(patientInDb.get().getCreatedTime(), is(notNullValue()));
		assertThat(patientInDb.get().getUpdatedTime(), is(notNullValue()));
		
	}

}
