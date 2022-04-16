package com.personal.pharmacy.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private EmployeeController controller;
	
	@MockBean
	private EmployeeService employeeService;
	
	@BeforeEach
	void setUp() {
		controller = new EmployeeController(employeeService);
	}
	
	@Test
	void test_Controller_IsNotNull() throws Exception {
		assertThat(controller, notNullValue());
	}

	@Test
	void test_GetById_ReturnsCorrectStatusAndEmployee_WhenGivenId1() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setFirstName("Rav");
		employee.setLastName("testing");
		
		when(employeeService.findById(1L)).thenReturn(Optional.of(employee));
		
		this.mockMvc.perform(get("/employee/1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("{'employeeId': 1, 'firstName': 'Rav'}")); 
	}
	
	@Test
	void test_GetById_ReturnsCorrectStatus_WhenGivenIdWithNoData() throws Exception {
		
		this.mockMvc.perform(get("/employee/5")).andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_Add_ReturnsCorrectStatus_WhenGivenValidEmployee() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmployeeId(2L);
		employee.setFirstName("test");
		employee.setLastName("testing");
		when(employeeService.save(employee)).thenReturn(employee);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/employee/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(employee)))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenInValidEmployee() throws Exception {
		
		Employee employee = new Employee();
		employee.setLastName("testing");
		employee.setEmployeeId(2L);
		when(employeeService.save(employee)).thenReturn(employee);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/employee/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(employee)))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"firstName\":\"Please enter a valid first name\"}"));
	}
	
	@Test
	void test_Delete_ReturnsCorrectStatus_WhenEmployeePresent() throws Exception {
		
		when(employeeService.delete(1L)).thenReturn(1);
		
		this.mockMvc.perform(delete("/employee/delete/1"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Delete_ReturnsNotFound_WhenGivenId5() throws Exception {
		this.mockMvc.perform(delete("/employee/delete/5"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_UpdateFirstName_CorrectlyUpdatesFirstName_WhenGivenFirstNameJohnAndId1() throws Exception {
		
		when(employeeService.updateFirstName(1L, "John")).thenReturn(1);
		
		this.mockMvc.perform(patch("/employee/1/updatefirstname").contentType(MediaType.APPLICATION_JSON_VALUE).content("John"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_UpdateFirstName_ReturnsNotFound_WhenGivenFirstNameJohnAndIdDoesNotExist() throws Exception {
		this.mockMvc.perform(patch("/employee/5/updatefirstname").contentType(MediaType.APPLICATION_JSON_VALUE).content("John"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_GetPrescriptions_ReturnsListOfPrescrptions_WhenGivenExistingId() throws Exception {
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		prescription.setPatientId(1L);
		prescription.setCreatedTime();
		prescription.setUpdatedTime();
		
		Prescription secondPrescription = new Prescription();
		secondPrescription.setPrescriptionId(2L);
		secondPrescription.setPatientId(1L);
		secondPrescription.setCreatedTime();
		secondPrescription.setUpdatedTime();
		
		when(employeeService.findPrescriptionsForEmployee(1L)).thenReturn(Arrays.asList(prescription, secondPrescription));
		
		this.mockMvc.perform(get("/employee/1/prescriptions"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect((jsonPath("$[0].prescriptionId", is(1))))
		.andExpect((jsonPath("$[0].patientId", is(1))))
		.andExpect((jsonPath("$[1].prescriptionId", is(2))))
		.andExpect((jsonPath("$[1].patientId", is(1))));
	}
	
	@Test
	void test_GetPrescriptions_ReturnsNotFound_WhenGivenNonExistingId() throws Exception {
		this.mockMvc.perform(get("/employee/5/prescriptions"))
		.andExpect(status().isNotFound());
	}
	
}
