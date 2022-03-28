package com.personal.pharmacy.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

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
    			+ "FIRST_NAME varchar(255), LAST_NAME varchar(255), ADDRESS varchar(255));");
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
		assertThat(savedEmployee.getEmployeeId(), not(equalTo(null)));
		assertThat(savedEmployee.getEmployeeId(), not(equalTo(0L)));
		
	}
	
//	@Test
//	void test_Delete_WorksCorrectly_WhenGivenId() {
//		
//        ReflectionTestUtils.setField(repository, "jdbcTemplate", jdbcTemplate);
//		Mockito.when(jdbcTemplate.update("DELETE FROM employees WHERE id=1", Integer.class)).thenReturn(1);
//		repository.deleteById(1L);
//	}
}
