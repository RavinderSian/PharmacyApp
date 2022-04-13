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

import com.personal.pharmacy.model.Medicine;

@SpringBootTest
@AutoConfigureTestDatabase
class MedicineRepositoryImplTest {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Autowired
	MedicineRepository repository;
    
    @BeforeEach
    void createTable() {
    	jdbcTemplate.execute("CREATE TABLE medicine ( id int NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "name varchar(50) NOT NULL, "
    			+ "dosage int,"
    			+ "duration varchar(50),"
    			+ "creation_timestamp DATETIME, "
    			+ "updated_timestamp DATETIME)");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS medicine");
    }
 
	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));
	}

	@Test
	void test_Save_ReturnsCorrectMedicine_WhenGivenMockMedicine() {
		
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		medicine.setCreatedTime();
		medicine.setUpdatedTime();
		
		repository.save(medicine);
		
		assertThat(medicine.getName(), equalTo(repository.findById(1L).get().getName()));
		assertThat(repository.findById(1L).get().getMedicineId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setCreatedTime();
		medicine.setUpdatedTime();
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		repository.save(medicine);
		assertThat(repository.findById(1L), not(equalTo(Optional.empty())));
		
		repository.deleteById(1L);
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenIdNotPresentInDatabase() {
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsCorrectMedicine_WhenIdPresentInDatabase() {
		
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setCreatedTime();
		medicine.setUpdatedTime();
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		repository.save(medicine);
		
		Optional<Medicine> medicineInDb = repository.findById(1L);
		
		assertThat(medicineInDb.get().getMedicineId(), equalTo(1L));
		assertThat(medicineInDb.get().getName(), equalTo("test"));
		assertThat(medicineInDb.get().getCreatedTime(), is(notNullValue()));
		assertThat(medicineInDb.get().getUpdatedTime(), is(notNullValue()));
		
	}
	
	@Test
	void test_UpdateFirstName_UpdatesNameCorrectly_WhenGivenId() {
		
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setCreatedTime();
		medicine.setUpdatedTime();
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		repository.save(medicine);
		
		assertThat(repository.updateName(1L, "updated"), equalTo(1));
		assertThat(repository.findById(1L).get().getName(), equalTo("updated"));
		
	}
	
	@Test
	void test_UpdateFirstName_ThrowsNoException_WhenIdDoesNotExist() {
		assertThat(repository.updateName(1L, "updated"), equalTo(0));
		
	}
	
	@Test
	void test_FindByName_ReturnsCorrectMedicineOptional_WhenMedicineExists() {
		
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setCreatedTime();
		medicine.setUpdatedTime();
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		repository.save(medicine);
		
		Optional<Medicine>  medicineInDbOptional = repository.findByName("test");
		
		assertThat(medicineInDbOptional.get().getName(), equalTo("test"));
		assertThat(medicineInDbOptional.get().getMedicineId(), equalTo(1L));

	}
	
	@Test
	void test_FindByName_ReturnsEmptyOptional_WhenMedicineDoesNotExists() {
		
		Optional<Medicine>  medicineInDbOptional = repository.findByName("test");
		
		assertThat(medicineInDbOptional.isEmpty(), equalTo(true));

	}

}
