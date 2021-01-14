package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Optional;

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
import com.personal.pharmacy.repository.EmployeeRepository;

@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	EmployeeController controller;
	
	@MockBean
	EmployeeRepository employeeRepository; //needed to stub behaviour

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void test_GetById_ReturnsCorrectStatusAndEmployee_WhenGivenId1() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setFirstName("Rav");
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		
		this.mockMvc.perform(get("/employee/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'employeeId': 1, 'firstName': 'Rav'}")); 
	}
	
	@Test
	public void test_GetById_ReturnsStringNoDataFoundForId5_WhenGivenIdWithNoData() throws Exception {
		
		this.mockMvc.perform(get("/employee/5")).andDo(print())
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5"));
	}
	
	@Test
	public void test_Add_ReturnsCorrectStatusAndEmployee_WhenGivenEmployee() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmployeeId(2L);
		employee.setFirstName("test");
		
		when(employeeRepository.save(employee)).thenReturn(employee);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(employee);
		
		this.mockMvc.perform(post("/employee/save").contentType(APPLICATION_JSON_UTF8).content(requestJson))
		.andExpect(status().isCreated())
		.andExpect(content().json("{'employeeId': 2, 'firstName': 'test'}"));
	}
	
	@Test
	public void test_UpdateFirstName_CorrectlyUpdatesFirstName_WhenGivenFirstNameJohnAndId1() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setFirstName("test");
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		
		this.mockMvc.perform(post("/employee/1/updatefirstname").contentType(APPLICATION_JSON_UTF8).content("John"))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'employeeId': 1, 'firstName': 'John'}"));
	}
	
	@Test
	public void test_UpdateFirstName_ReturnsNoDataForId5_WhenGivenFirstNameJohnAndId5() throws Exception {
		
		this.mockMvc.perform(post("/employee/5/updatefirstname").contentType(APPLICATION_JSON_UTF8).content("John"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5"));
	}
	
}
