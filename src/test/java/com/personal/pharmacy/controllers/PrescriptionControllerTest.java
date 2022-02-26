package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.PrescriptionService;

@WebMvcTest(PrescriptionController.class)
class PrescriptionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private PrescriptionController controller;
	
	@MockBean
	private PrescriptionService prescriptionService;
	
//	@BeforeEach
//	void setUp() {
//		controller = new PrescriptionController(prescriptionService);
//	}
	
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void test_GetById_ReturnsCorrectStatusAndPrescription_WhenGivenId1() throws Exception {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		when(prescriptionService.findById(1L)).thenReturn(Optional.of(prescription));
		
		this.mockMvc.perform(get("/prescription/1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("{'prescriptionId': 1}")); 
	}
	
	@Test
	void test_GetById_ReturnsNoDataFoundForId5_WhenGivenId5() throws Exception {		
		this.mockMvc.perform(get("/prescription/5")).andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_Save_ReturnsCorrectStatusAndPrescription_WhenGivenPrescription() throws Exception {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		when(prescriptionService.save(prescription)).thenReturn(prescription);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/prescription/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(prescription)))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Delete_ReturnsCorrectStatus_WhenPrescriptionPresent() throws Exception {
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		when(prescriptionService.findById(1L)).thenReturn(Optional.of(prescription));
		
		this.mockMvc.perform(delete("/prescription/delete/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("test"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Delete_ReturnsNotFound_WhenGivenId5() throws Exception {
		this.mockMvc.perform(delete("/prescription/delete/5").contentType(MediaType.APPLICATION_JSON_VALUE).content("test"))
		.andExpect(status().isNotFound());
	}
	
}
