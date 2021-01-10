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
import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.repository.PatientRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

	@Autowired
	PatientController controller;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PatientRepository patientRepository;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void test_GetById_ReturnsCorrectStatusAndPatient_WhenGivenId1() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("rav");
		
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
		
		this.mockMvc.perform(get("/patient/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'patientId': 1, 'firstName': 'rav'}")); 
	}
	
	@Test
	public void test_GetById_ReturnsStringNoDataFoundForId5_WhenGivenIdWithNoData() throws Exception {
		
		this.mockMvc.perform(get("/patient/5")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().string("No data found for id 5"));
	}
	
	@Test
	public void test_Save_ReturnsCorrectStatusAndPatient_WhenGivenPatient() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("rav");
		
		when(patientRepository.save(patient)).thenReturn(patient);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(patient);
		
		this.mockMvc.perform(post("/patient/save").contentType(APPLICATION_JSON_UTF8).content(requestJson))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'patientId': 1, 'firstName': 'rav'}"));
	}
	
	@Test
	public void test_UpdateFirstName_CorrectlyUpdatesFirstName_WhenGivenFirstNameJohnAndId1() throws Exception {
		
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setFirstName("rav");
		
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
		
		this.mockMvc.perform(post("/patient/1/updatefirstname").contentType(APPLICATION_JSON_UTF8).content("John"))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'patientId': 1, 'firstName': 'John'}"));
	}
	
	@Test
	public void test_UpdateFirstName_ReturnsNoDataForId5_WhenGivenFirstNameJohnAndId5() throws Exception {
		
		this.mockMvc.perform(post("/patient/5/updatefirstname").contentType(APPLICATION_JSON_UTF8).content("John"))
		.andExpect(status().isAccepted())
		.andExpect(content().string("No data found for id 5"));
	}
	
}
