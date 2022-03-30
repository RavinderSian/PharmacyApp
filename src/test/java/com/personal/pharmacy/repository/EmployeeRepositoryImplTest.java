package com.personal.pharmacy.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.personal.pharmacy.model.Employee;

@SpringBootTest
@AutoConfigureTestDatabase
class EmployeeRepositoryImplTest {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Autowired
	EmployeeRepository repository;
    
    @BeforeEach
    void createTable() {
    	jdbcTemplate.execute("CREATE TABLE EMPLOYEES ( ID int NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "FIRST_NAME varchar(50) NOT NULL, LAST_NAME varchar(50) NOT NULL);");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS EMPLOYEES");
    }
 
	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));
	}

	@Test
	void test_Save_ReturnsCorrectEmployee_WhenGivenMockEmployee() {
		
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		
		Employee savedEmployee = repository.save(employee);
		
		assertThat(employee.getFirstName(), equalTo(savedEmployee.getFirstName()));
		assertThat(employee.getLastName(), equalTo(savedEmployee.getLastName()));
		assertThat(repository.findById(1L).get().getEmployeeId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		
		repository.save(employee);
		assertThat(repository.findById(1L), not(equalTo(Optional.empty())));
		
		repository.deleteById(1L);
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenIdNotPresentInDatabase() {
		assertThat(repository.findById(1L), equalTo(Optional.empty()));
	}
	
 }
