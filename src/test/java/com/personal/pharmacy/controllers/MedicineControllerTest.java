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
import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.services.MedicineService;

@WebMvcTest(MedicineController.class)
class MedicineControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private MedicineController controller;
	
	@MockBean
	private MedicineService service;
	
	@BeforeEach
	void setUp() {
		controller = new MedicineController(service);
	}
	
	@Test
	void controller_IsNotNull() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void test_GetById_ReturnsCorrectStatusAndMedicine_WhenGivenId1() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setName("paracetamol");
		medicine.setMedicineId(1L);
		when(service.findById(1L)).thenReturn(Optional.of(medicine));
		
		this.mockMvc.perform(get("/medicine/1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("{'medicineId': 1, 'name': 'paracetamol'}")); 
	}
	
	@Test
	void test_GetById_ReturnsNoDataForId5_WhenGivenId5() throws Exception {
		this.mockMvc.perform(get("/medicine/5")).andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_Add_ReturnsCorrectStatus_WhenGivenValidMedicine() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setName("paracetamol");
		medicine.setMedicineId(1L);
		when(service.save(medicine)).thenReturn(medicine);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/medicine/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(medicine)))
			.andExpect(status().isOk());
	}
	
	@Test
	void test_Add_ReturnsCorrectStatusAndResponse_WhenGivenInValidMedicine() throws Exception {
		
		Medicine medicine = new Medicine();
		medicine.setMedicineId(1L);
		when(service.save(medicine)).thenReturn(medicine);
	    ObjectMapper mapper = new ObjectMapper();
		
		this.mockMvc.perform(put("/medicine/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writer().writeValueAsString(medicine)))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"name\":\"Please enter a valid name\"}"));
	}
	
	
	@Test
	void test_UpdateName_CorrectlyUpdatesName_WhenGivenNameNewAndId1() throws Exception {
		
		when(service.updateName(1L, "new")).thenReturn(1);
		
		this.mockMvc.perform(patch("/medicine/1/updatename").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_UpdateName_ReturnsNoData_WhenGivenNameTestAndId5() throws Exception {
		this.mockMvc.perform(patch("/medicine/5/updatename").contentType(MediaType.APPLICATION_JSON_VALUE).content("test"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_Delete_ReturnsCorrectStatus_WhenMedicinePresent() throws Exception {
		
		when(service.delete(1L)).thenReturn(1);
		
		this.mockMvc.perform(delete("/medicine/delete/1").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}
	
	@Test
	void test_Delete_ReturnsNotFound_WhenMedicineNotPresent() throws Exception {
		this.mockMvc.perform(delete("/medicine/delete/5").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void test_GetIngredients_ReturnsListOfIngredients_WhenGivenExistingId() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(1L);
		ingredient.setName("test");
		ingredient.setMedicineId(1L);
		
		Ingredient secondIngredient = new Ingredient();
		secondIngredient.setIngredientId(2L);
		secondIngredient.setName("testing");
		secondIngredient.setMedicineId(1L);
		
		when(service.findIngredientsByMedicine(1L)).thenReturn(Arrays.asList(ingredient, secondIngredient));
		
		this.mockMvc.perform(get("/medicine/1/ingredients"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect((jsonPath("$[0].ingredientId", is(1))))
		.andExpect((jsonPath("$[0].name", is("test"))))
		.andExpect((jsonPath("$[0].medicineId", is(1))))
		.andExpect((jsonPath("$[1].ingredientId", is(2))))
		.andExpect((jsonPath("$[1].name", is("testing"))))
		.andExpect((jsonPath("$[1].medicineId", is(1))));
	}
	
	@Test
	void test_GetIngredients_ReturnsNotFound_WhenGivenNonExistingId() throws Exception {
		this.mockMvc.perform(get("/medicine/5/ingredients"))
		.andExpect(status().isNotFound());
	}
	
}
