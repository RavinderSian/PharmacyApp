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
import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.services.IngredientService;
import com.personal.pharmacy.services.MedicineService;

@AutoConfigureMockMvc
@SpringBootTest
public class IngredientControllerTest {

	@Autowired
	IngredientController controller;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IngredientService ingredientService;

	@MockBean
	MedicineService medicineService;

	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void test_GetById_ReturnsCorrectStatusAndIngredient_WhenGivenId1() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("paracetamol");
		ingredient.setIngredientId(1L);
		
		when(ingredientService.findById(1L)).thenReturn(Optional.of(ingredient));
		
		this.mockMvc.perform(get("/ingredient/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'ingredientId': 1, 'name': 'paracetamol'}")); 
	}
	
	@Test
	public void test_GetById_ReturnsNoDataFoundForId5_WhenGivenId5() throws Exception {
		
		this.mockMvc.perform(get("/ingredient/5")).andDo(print())
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5")); 
	}
	
	@Test
	public void test_Add_ReturnsCorrectStatusAndIngredient_WhenGivenIngredient() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("paracetamol");
		ingredient.setIngredientId(1L);
		
		when(ingredientService.save(ingredient)).thenReturn(ingredient);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(ingredient);
		
		this.mockMvc.perform(post("/ingredient/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isCreated())
		.andExpect(content().json("{'ingredientId': 1, 'name': 'paracetamol'}"));
	}
	
	@Test
	public void test_Add_ReturnsCorrectStatusAndIngredient_WhenGivenInValidIngredient() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(1L);
		
		when(ingredientService.save(ingredient)).thenReturn(ingredient);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(ingredient);
		
		this.mockMvc.perform(post("/ingredient/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("[Please enter a valid name]"));
	}
	
	@Test
	public void test_UpdateName_CorrectlyUpdatesName_WhenGivenNameSalicylicAcidAndId1() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("paracetamol");
		ingredient.setIngredientId(1L);
		
		when(ingredientService.findById(1L)).thenReturn(Optional.of(ingredient));
		ingredient.setName("new");
		when(ingredientService.updateIngredientName(ingredient, "new")).thenReturn(ingredient);
		this.mockMvc.perform(post("/ingredient/1/updatename").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'ingredientId': 1, 'name': 'new'}"));
	}
	
	@Test
	public void test_UpdateName_ReturnsNoDataForId5_WhenGivenNameNewAndId5() throws Exception {
		
		this.mockMvc.perform(post("/ingredient/5/updatename").contentType(MediaType.APPLICATION_JSON_VALUE).content("new"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5"));
	}
	
}
