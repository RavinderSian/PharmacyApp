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

import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.model.Medicine;

@SpringBootTest
@AutoConfigureTestDatabase
class IngredientRepositoryImplTest {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Autowired
	IngredientRepository repository;
    
    @Autowired
	MedicineRepository medicineRepository;
    
    @BeforeEach
    void createTable() {
    	
    	jdbcTemplate.execute("CREATE TABLE medicine ( id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "name varchar(50) NOT NULL, dosage int, duration varchar(50), creation_timestamp DATETIME, "
    			+ "updated_timestamp DATETIME)");
    	
    	jdbcTemplate.execute("CREATE TABLE ingredient ( ID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "NAME varchar(50) NOT NULL, CREATION_TIMESTAMP DATETIME, UPDATED_TIMESTAMP DATETIME, "
    			+ "medicine_id bigint REFERENCES medicine(id))");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS ingredient");
    	jdbcTemplate.execute("DROP TABLE IF EXISTS medicine");

    }
 
	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));
	}

	@Test
	void test_Save_ReturnsCorrectIngredient_WhenGivenMockIngredient() {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("test");
		repository.save(ingredient);
		
		assertThat(ingredient.getName(), equalTo(repository.findById(1L).get().getName()));
		assertThat(repository.findById(1L).get().getIngredientId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("test");
		repository.save(ingredient);
		assertThat(repository.findById(1L), not(equalTo(Optional.empty())));
		
		repository.deleteById(1L);
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenIdNotPresentInDatabase() {
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsCorrectIngredient_WhenIdPresentInDatabase() {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("test");
		repository.save(ingredient);
		
		Optional<Ingredient> ingredientInDb = repository.findById(1L);
		
		assertThat(ingredientInDb.get().getIngredientId(), equalTo(1L));
		assertThat(ingredientInDb.get().getName(), equalTo("test"));
		assertThat(ingredientInDb.get().getCreatedTime(), is(notNullValue()));
		assertThat(ingredientInDb.get().getUpdatedTime(), is(notNullValue()));
		
	}
	
	@Test
	void test_UpdateName_UpdatesNameCorrectly_WhenGivenId() {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("test");
		repository.save(ingredient);
		
		assertThat(repository.updateName(1L, "updated"), equalTo(1));
		assertThat(repository.findById(1L).get().getName(), equalTo("updated"));
		
	}
	
	@Test
	void test_UpdateFirstName_ThrowsNoException_WhenIdDoesNotExist() {
		assertThat(repository.updateName(1L, "updated"), equalTo(0));
		
	}
	
	@Test
	void test_FindIngredientsForMedicine_ReturnsListOfIngredients_WhenIngredientsExistForMedicine() {
		 
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setDosage(2);
		medicine.setDuration("testing");
		
		medicineRepository.save(medicine);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("test");
		ingredient.setMedicineId(1L);
		repository.save(ingredient);
		
		Ingredient secondIngredient = new Ingredient();
		secondIngredient.setName("testing");
		secondIngredient.setMedicineId(1L);
		repository.save(secondIngredient);
		
		assertThat(repository.findIngredientsByMedicine(1L).size(), equalTo(2));

		
	}
	
	@Test
	void test_FindIngredientsForMedicine_ReturnsListOfIngredients_WhenIngredientsDoNotExistForMedicine() {
		 
		Medicine medicine = new Medicine();
		medicine.setName("test");
		medicine.setDosage(2);
		
		medicineRepository.save(medicine);
		
		assertThat(repository.findIngredientsByMedicine(1L).size(), equalTo(0));
		
	}
	
	@Test
	void test_FindIngredientsForMedicine_ReturnsListOfIngredients_WhenMedicineDoesNotExist() {
		 
		assertThat(repository.findIngredientsByMedicine(1L).size(), equalTo(0));
		
	}
	
}
