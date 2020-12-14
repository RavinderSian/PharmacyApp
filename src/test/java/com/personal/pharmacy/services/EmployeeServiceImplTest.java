package com.personal.pharmacy.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.repository.EmployeeRepository;

@SpringBootTest
class EmployeeServiceImplTest {

	//@Mock never mock the service being tested
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	Employee employeeMock;
	
	@BeforeEach
	public void setUp() throws Exception {
		employeeService = new EmployeeServiceImpl(employeeRepository);
	}
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		//Act
		employeeService.save(employeeMock);
		//Assert
		verify(employeeRepository, times(1)).save(employeeMock);
	}
	
	@Test
	public void test_Save_ReturnsCorrectEmployee_WhenGivenEmployeeMock() {
		//Assert
		when(employeeRepository.save(employeeMock)).thenReturn(employeeMock);
		//Act
		Employee employee = employeeService.save(employeeMock);
		//Assert
		Assertions.assertEquals(employeeMock, employee);
	}
	
	@Test
	public void test_Delete_Calls_RepositoryDelete_WhenCalled() {
		//Assert
		when(employeeRepository.save(employeeMock)).thenReturn(employeeMock);
		//Act
		employeeService.save(employeeMock);
		//Assert
		verify(employeeRepository, times(1)).save(employeeMock);
	}
	
	@Test
	public void test_FindAll_Calls_RepositoryFindAll_WhenCalled() {
		//Act
		employeeService.findAll();
		//Assert
		verify(employeeRepository, times(1)).findAll();
	}
	
	@Test
	public void test_FindAll_ReturnsEmptyList_WhenGivenNothing() {
		//Arrange
		when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
		//Act
		List<Employee> employees = employeeService.findAll();
		//Assert
		Assertions.assertEquals(0, employees.size());
	}
	
	
	@Test
	public void test_FindAll_ReturnsListOfSize1_WhenGivenEmployeeMock() {
		//Arrange
		when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>(Arrays.asList(employeeMock)));
		//Act
		List<Employee> employees = employeeService.findAll();
		//Assert
		Assertions.assertEquals(1, employees.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize2_WhenGivenDuplicateEmployeeMocks() {
		//Arrange
		when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>(Arrays.asList(employeeMock, employeeMock)));
		//Act
		List<Employee> employees = employeeService.findAll();
		//Assert
		Assertions.assertEquals(2, employees.size());
	}
	
	@Test
	public void test_FindById_ReturnsEmployeeMock_WhenCalledWithId1() {
		//Arrange
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeMock));
		//Act
		Employee employee = employeeService.findById(1L);
		//Assert
		Assertions.assertEquals(employeeMock, employee);
	}
	
	@Test
	public void test_FindById_ReturnsRunTimeException_WhenCalledWithId1() {

		Assertions.assertThrows(RuntimeException.class, () -> employeeService.findById(1L));
	}
	
}
