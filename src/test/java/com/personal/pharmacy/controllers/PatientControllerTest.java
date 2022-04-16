package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
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
import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.PatientService;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private PatientController controller;

	@MockBean
	private PatientService patientService;
	
	@BeforeEach
	void setUp() {
		controller = new PatientController(patientService);
	}
	
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void test_GetById_ReturnsCorrectStatusAndPatient_WhenGivenId1() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("rav");
		when(patientService.findById(1L)).thenReturn(Optional.of(patient));
		
		this.mockMvc.perform(get("/patient/1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("{'patientId': 1, 'firstName': 'rav'}")); 
	}
	
	@Test
	void test_GetById_ReturnsStringNoDataFoundForId5_WhenGivenIdWithNoData() throws Exception {
		
		this.mockMvc.perform(get("/patient/5"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_Save_ReturnsCorrectStatus_WhenGivenValidPatient() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("rav");
		patient.setLastName("sian");
		when(patientService.save(patient)).thenReturn(patient);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/patient/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(patient)))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Save_ReturnsCorrectStatusAndPatient_WhenGivenInValidPatient() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setLastName("testing");
		when(patientService.save(patient)).thenReturn(patient);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/patient/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(patient)))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"firstName\":\"Please enter a valid first name\"}"));
	}
	
	@Test
	void test_Delete_ReturnsCorrectStatus_WhenPatientPresent() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setLastName("testing");
		when(patientService.delete(1L)).thenReturn(1);
		
		this.mockMvc.perform(delete("/patient/delete/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("test"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Delete_ReturnsNotFound_WhenGivenId5() throws Exception {
		this.mockMvc.perform(delete("/patient/delete/5").contentType(MediaType.APPLICATION_JSON_VALUE).content("test"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_UpdateFirstName_CorrectlyUpdatesFirstName_WhenGivenFirstNameJohnAndId1() throws Exception {
		
		when(patientService.updateFirstName(1L, "John")).thenReturn(1);
		
		this.mockMvc.perform(patch("/patient/1/updatefirstname").contentType(MediaType.APPLICATION_JSON_VALUE).content("John"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_UpdateFirstName_ReturnsNoDataForId5_WhenGivenFirstNameJohnAndId5() throws Exception {
		this.mockMvc.perform(patch("/patient/5/updatefirstname").contentType(MediaType.APPLICATION_JSON_VALUE).content("John"))
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
		
		when(patientService.findPrescriptionsForPatient(1L)).thenReturn(Arrays.asList(prescription, secondPrescription));
		
		this.mockMvc.perform(get("/patient/1/prescriptions"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect((jsonPath("$[0].prescriptionId", is(1))))
		.andExpect((jsonPath("$[0].patientId", is(1))))
		.andExpect((jsonPath("$[1].prescriptionId", is(2))))
		.andExpect((jsonPath("$[1].patientId", is(1))));
	}
	
	@Test
	void test_GetPrescriptions_ReturnsNotFound_WhenGivenNonExistingId() throws Exception {
		this.mockMvc.perform(get("/patient/5/prescriptions"))
		.andExpect(status().isNotFound());
	}
	
}
