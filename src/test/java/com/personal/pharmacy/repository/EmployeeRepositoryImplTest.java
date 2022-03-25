package com.personal.pharmacy.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
class EmployeeRepositoryImplTest {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
    private SimpleJdbcInsert simpleJdbcInsert;
	
	private EmployeeRepository repository = new EmployeeRepositoryImpl();

	@Test
	void test_notNull() throws Exception {
		assertThat(jdbcTemplate, not(equalTo(null)));
		assertThat(simpleJdbcInsert, not(equalTo(null)));
		assertThat(repository, not(equalTo(null)));

	}

//	@Test
//	void test_Save_ReturnsCorrectEmployee_WhenGivenMockEmployee() {
//		Employee employee = new Employee();
//		employee.setFirstName("test");
//		employee.setLastName("testing");
//		
//		//when(simpleJdbcInsert.withTableName("employees").usingGeneratedKeyColumns("id")).thenReturn(simpleJdbcInsert);
//		
//		Employee savedEmployee = repository.save(employee);
//		
//		assertThat(employee.getFirstName(), equalTo(savedEmployee.getFirstName()));
//		
//	}
	
	@Test
	void test_Delete_WorksCorrectly_WhenGivenId() {
		
        ReflectionTestUtils.setField(repository, "jdbcTemplate", jdbcTemplate);
		Mockito.when(jdbcTemplate.update("DELETE FROM employees WHERE id=1", Integer.class)).thenReturn(1);
		repository.deleteById(1L);
	}
}
