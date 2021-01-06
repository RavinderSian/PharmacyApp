package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	EmployeeController controller;
	
	MockBean bean;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void test_GetById_ReturnsCorrectStatusAndEmployee() throws Exception {
		
		this.mockMvc.perform(get("/employee/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'employeeId': 1, 'firstName': 'new name', 'lastName': null, 'prescription': null}"));
	}
	
//	@Test
//	public void test_Save_ReturnsCorrectStatusAndEmployee() throws Exception {
//		
//		this.mockMvc.perform(get("/employee/1")).andDo(print())
//		.andExpect(status().isAccepted())
//		.andExpect(content().json("{'employeeId': 1, 'firstName': 'new name', 'lastName': null, 'prescription': null}"));
//	}
	
}
