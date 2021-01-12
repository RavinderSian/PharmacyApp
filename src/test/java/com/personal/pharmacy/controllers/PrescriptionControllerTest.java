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
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.PrescriptionRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PrescriptionControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	PrescriptionController controller;
	
	@MockBean
	PrescriptionRepository prescriptionRepository;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void test_GetById_ReturnsCorrectStatusAndPrescription_WhenGivenId1() throws Exception {
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		
		when(prescriptionRepository.findById(1L)).thenReturn(Optional.of(prescription));
		
		this.mockMvc.perform(get("/prescription/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'prescriptionId': 1}")); 
	}
	
	@Test
	public void test_GetById_ReturnsNoDataFoundForId5_WhenGivenId5() throws Exception {		
		
		this.mockMvc.perform(get("/prescription/5")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().string("No data found for id 5")); 
	}
	
	@Test
	public void test_Save_ReturnsCorrectStatusAndPrescription_WhenGivenPrescription() throws Exception {
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		
		when(prescriptionRepository.save(prescription)).thenReturn(prescription);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(prescription);
		
		this.mockMvc.perform(post("/prescription/save").contentType(APPLICATION_JSON_UTF8).content(requestJson))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'prescriptionId': 1}")); 
	}
	
}
