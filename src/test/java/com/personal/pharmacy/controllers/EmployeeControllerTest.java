package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.personal.pharmacy.model.Employee;

@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	EmployeeController controller;
	
	MockBean bean;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
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
	
	@Test
	public void test_Save_ReturnsCorrectStatusAndEmployee_WhenGivenEmployee() throws Exception {
		
		Employee employee = new Employee();
		employee.setFirstName("test");
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(employee);
		
		this.mockMvc.perform(post("/employee/save").contentType(APPLICATION_JSON_UTF8).content(requestJson))
		.andExpect(status().isCreated())
		.andExpect(content().json("{'employeeId': 2, 'firstName': 'test', 'lastName': null, 'prescription': null}"));
	}
	
	@Test
	public void test_UpdateFirstName_CorrectlyUpdatesFirstName_WhenGivenFirstNameJohn() throws Exception {
		
		Employee employee = new Employee();
		employee.setFirstName("test");
				
		this.mockMvc.perform(post("/employee/1/updatefirstname").contentType(APPLICATION_JSON_UTF8).content("John"))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'employeeId': 1, 'firstName': 'John', 'lastName': null, 'prescription': null}"));
	}
	
}
