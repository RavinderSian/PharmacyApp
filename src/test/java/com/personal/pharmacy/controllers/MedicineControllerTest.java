package com.personal.pharmacy.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.services.MedicineService;

@AutoConfigureMockMvc
@SpringBootTest
public class MedicineControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	MedicineController controller;
	
	@MockBean
	MedicineService medicineService;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void test_GetById_ReturnsCorrectStatusAndMedicine_WhenGivenId1() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setName("paracetamol");
		medicine.setMedicineId(1L);
		
		when(medicineService.findById(1L)).thenReturn(Optional.of(medicine));
		
		this.mockMvc.perform(get("/medicine/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'medicineId': 1, 'name': 'paracetamol'}")); 
	}
	
	@Test
	public void test_GetById_ReturnsNoDataForId5_WhenGivenId5() throws Exception {
		
		this.mockMvc.perform(get("/medicine/5")).andDo(print())
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5")); 
	}
	
	@Test
	public void test_Add_ReturnsCorrectStatusAndMedicine_WhenGivenValidMedicine() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setName("paracetamol");
		medicine.setMedicineId(1L);
		
		when(medicineService.save(medicine)).thenReturn(medicine);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(medicine);
		
		this.mockMvc.perform(post("/medicine/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
			.andExpect(status().isCreated())
			.andExpect(content().json("{'medicineId': 1, 'name': 'paracetamol'}"));
	}
	
	@Test
	public void test_Add_ReturnsCorrectStatusAndMedicine_WhenGivenInValidMedicine() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setMedicineId(1L);
		
		when(medicineService.save(medicine)).thenReturn(medicine);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(medicine);
		
		this.mockMvc.perform(post("/medicine/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("[Please enter a valid name]"));
	}
	
	
	@Test
	public void test_UpdateName_CorrectlyUpdatesName_WhenGivenNameNewAndId1() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setName("paracetamol");
		medicine.setMedicineId(1L);
		
		when(medicineService.findById(1L)).thenReturn(Optional.of(medicine));
		medicine.setName("new");
		when(medicineService.updateName(medicine, "new")).thenReturn(medicine);
		this.mockMvc.perform(post("/medicine/1/updatename").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'medicineId': 1, 'name': 'new'}"));
	}
	
	@Test
	public void test_UpdateName_ReturnsNoDataForId5_WhenGivenNameTestAndId5() throws Exception {
				
		this.mockMvc.perform(post("/medicine/5/updatename").contentType(MediaType.APPLICATION_JSON_VALUE).content("test"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5"));
	}
	
}
