package com.personal.pharmacy.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.services.EmployeeService;

class EmployeeControllerTest {
	
	@Mock
	EmployeeService employeeService;
	
	@Mock
	Model model; 
	
	EmployeeController controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new EmployeeController(employeeService);
	}
	
	@Test
	public void test_Get_FindById_ReturnsCorrectStatus() throws Exception{
		//mock setup
		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setFirstName("rav");
		when(employeeService.findById(1L));
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/employee/{id}", 1L))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'employeeId': 1, 'firstName': new name, 'lastName': null, 'prescription': null}"));
	}
	
	

}
