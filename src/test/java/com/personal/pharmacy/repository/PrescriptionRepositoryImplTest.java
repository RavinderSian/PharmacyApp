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

import com.personal.pharmacy.model.Prescription;

@AutoConfigureTestDatabase
@SpringBootTest
class PrescriptionRepositoryImplTest {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Autowired
	PrescriptionRepository repository;
    
    @BeforeEach
    void createTable() {
    	//create patient table before hand as it is referenced later
    	jdbcTemplate.execute("CREATE TABLE patient ( ID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "FIRST_NAME varchar(50) NOT NULL, LAST_NAME varchar(50) NOT NULL, "
    			+ "CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME);");
    	
    	jdbcTemplate.execute("CREATE TABLE prescription ( ID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME, "
    			+ "fk_patient_id bigint REFERENCES patient(id));");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS prescription");
    	jdbcTemplate.execute("DROP TABLE IF EXISTS patient");

    }
 
	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));
	}

	@Test
	void test_Save_ReturnsCorrectPrescription_WhenGivenMockPrescription() {
		
		Prescription prescription = new Prescription();
		prescription.setCreatedTime();
		prescription.setUpdatedTime();
		repository.save(prescription);
		
		assertThat(repository.findById(1L).get().getPrescriptionId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Prescription prescription = new Prescription();
		prescription.setCreatedTime();
		prescription.setUpdatedTime();
		repository.save(prescription);
		assertThat(repository.findById(1L), not(equalTo(Optional.empty())));
		
		repository.deleteById(1L);
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenIdNotPresentInDatabase() {
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsCorrectPrescription_WhenIdPresentInDatabase() {
		
		Prescription prescription = new Prescription();
		prescription.setCreatedTime();
		prescription.setUpdatedTime();
		repository.save(prescription);
		
		Optional<Prescription> prescriptionInDb = repository.findById(1L);
		
		assertThat(prescriptionInDb.get().getPrescriptionId(), equalTo(1L));
		assertThat(prescriptionInDb.get().getCreatedTime(), is(notNullValue()));
		assertThat(prescriptionInDb.get().getUpdatedTime(), is(notNullValue()));
		
	}

}
