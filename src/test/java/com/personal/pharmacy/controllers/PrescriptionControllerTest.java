package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.services.MedicineService;
import com.personal.pharmacy.services.PrescriptionService;

@WebMvcTest(PrescriptionController.class)
class PrescriptionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private PrescriptionController controller;
	
	@MockBean
	private PrescriptionService service;
	
	@MockBean
	private MedicineService medicineService;
	
	@BeforeEach
	void setUp() {
		controller = new PrescriptionController(service, medicineService);
	}
	
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void test_GetById_ReturnsCorrectStatusAndPrescription_WhenGivenId1() throws Exception {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		when(service.findById(1L)).thenReturn(Optional.of(prescription));
		
		this.mockMvc.perform(get("/prescription/1"))
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
		prescription.setEmployeeId(1L);
		prescription.setPatientId(1L);
		when(service.save(prescription)).thenReturn(prescription);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/prescription/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(prescription)))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Save_ReturnsCorrectStatusAndResponse_WhenNoEmployeeId() throws Exception {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		prescription.setPatientId(1L);
		when(service.save(prescription)).thenReturn(prescription);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/prescription/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(prescription)))
		.andExpect(status().isBadRequest())
		.andExpect(content().json("{'employeeId' : 'Please enter a valid employee id'}"));
	}
	
	@Test
	void test_Save_ReturnsCorrectStatusAndResponse_WhenNoPatientId() throws Exception {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1L);
		prescription.setEmployeeId(1L);
		when(service.save(prescription)).thenReturn(prescription);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/prescription/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(prescription)))
		.andExpect(status().isBadRequest())
		.andExpect(content().json("{'patientId' : 'Please enter a valid patient id'}"));
	}
	
	@Test
	void test_Delete_ReturnsCorrectStatus_WhenPrescriptionPresent() throws Exception {
		
		when(service.delete(1L)).thenReturn(1);
		
		this.mockMvc.perform(delete("/prescription/delete/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
	
	@Test
	void test_Delete_ReturnsNotFound_WhenGivenId5() throws Exception {
		this.mockMvc.perform(delete("/prescription/delete/5")).andExpect(status().isNotFound());
	}

	@Test
	void test_AddMedicine_ReturnsCorrectResponse_WhenMedicineAndPrescriptionIdsExist() throws Exception {
		
		when(service.findById(1L)).thenReturn(Optional.of(new Prescription()));
		
		
		when(medicineService.findById(1L)).thenReturn(Optional.of(new Medicine()));
		
		when(service.addMedicine(1L, 1L)).thenReturn(1);
		
		this.mockMvc.perform(get("/prescription/1/addmedicine/1")).andExpect(status().isOk());
	}
	
	@Test
	void test_AddMedicine_ReturnsNotFound_WhenGivenMedicineIdThatDoesNotExist() throws Exception {
		this.mockMvc.perform(get("/prescription/3/addmedicine/2")).andExpect(status().isNotFound());
	}
	
	@Test
	void test_AddMedicine_ReturnsNotFound_WhenGivenPrescriptionIdThatDoesNotExist() throws Exception {
		
		when(service.findById(1L)).thenReturn(Optional.of(new Prescription()));
		
		this.mockMvc.perform(get("/prescription/1/addmedicine/2")).andExpect(status().isNotFound());
	}
	
	@Test
	void test_GetMedicines_ReturnsCorrectResponse_WhenGivenPrescriptionIdThatExistAndHasMedicines() throws Exception {
		
		when(service.findById(1L)).thenReturn(Optional.of(new Prescription()));
		
		Medicine medicine = new Medicine();
		medicine.setMedicineId(1L);
		medicine.setName("test");
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		
		Medicine secondMedicine = new Medicine();
		secondMedicine.setMedicineId(2L);
		secondMedicine.setName("testing");
		secondMedicine.setDosage(2);
		secondMedicine.setDuration("2 day");
		
		when(medicineService.getMedicinesForPrescription(1L)).thenReturn(Arrays.asList(medicine, secondMedicine));
		
		this.mockMvc.perform(get("/prescription/1/medicines")).andExpect(status().isOk())
		.andExpect((jsonPath("$[0].medicineId", is(1))))
		.andExpect((jsonPath("$[0].name", is("test"))))
		.andExpect((jsonPath("$[0].duration", is("1 day"))))
		.andExpect((jsonPath("$[0].dosage", is(1))))
		.andExpect((jsonPath("$[1].medicineId", is(2))))
		.andExpect((jsonPath("$[1].name", is("testing"))))
		.andExpect((jsonPath("$[1].duration", is("2 day"))))
		.andExpect((jsonPath("$[1].dosage", is(2))))
;
	}
	
	@Test
	void test_GetMedicines_ReturnsNotFound_WhenGivenPrescriptionIdThatDoesNotExist() throws Exception {
		
		this.mockMvc.perform(get("/prescription/1/medicines")).andExpect(status().isNotFound());
	}
	
}
