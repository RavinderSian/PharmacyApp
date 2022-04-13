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
    	jdbcTemplate.execute("CREATE TABLE employees ( ID int NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    			+ "FIRST_NAME varchar(50) NOT NULL, LAST_NAME varchar(50) NOT NULL, "
    			+ "CREATION_TIMESTAMP DATETIME, "
    			+ "UPDATED_TIMESTAMP DATETIME)");
    }
    
    @AfterEach
    void deleteTable() {
    	jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
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
		employee.setCreatedTime();
		employee.setUpdatedTime();
		repository.save(employee);
		
		assertThat(employee.getFirstName(), equalTo(repository.findById(1L).get().getFirstName()));
		assertThat(employee.getLastName(), equalTo(repository.findById(1L).get().getLastName()));
		assertThat(repository.findById(1L).get().getEmployeeId(), equalTo(1L));
		
	}
	
	@Test
	void test_Delete_DeletesEntity_WhenGivenId() {
		
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		employee.setCreatedTime();
		employee.setUpdatedTime();
		repository.save(employee);
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
		
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		employee.setCreatedTime();
		employee.setUpdatedTime();
		repository.save(employee);
		
		Optional<Employee> employeeInDb = repository.findById(1L);
		
		assertThat(employeeInDb.get().getEmployeeId(), equalTo(1L));
		assertThat(employeeInDb.get().getFirstName(), equalTo("test"));
		assertThat(employeeInDb.get().getLastName(), equalTo("testing"));
		assertThat(employeeInDb.get().getCreatedTime(), is(notNullValue()));
		assertThat(employeeInDb.get().getUpdatedTime(), is(notNullValue()));
		
	}
	
	@Test
	void test_UpdateFirstName_UpdatesNameCorrectly_WhenGivenId() {
		
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		employee.setCreatedTime();
		employee.setUpdatedTime();
		repository.save(employee);
		
		assertThat(repository.updateFirstName(1L, "updated"), equalTo(1));
		assertThat(repository.findById(1L).get().getFirstName(), equalTo("updated"));
		
	}
	
	@Test
	void test_UpdateFirstName_ThrowsNoException_WhenIdDoesNotExist() {
		assertThat(repository.updateFirstName(1L, "updated"), equalTo(0));
		
	}
	
 }
