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
import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.repository.IngredientRepository;
import com.personal.pharmacy.repository.MedicineRepository;

@AutoConfigureMockMvc
@SpringBootTest
public class IngredientControllerTest {

	@Autowired
	IngredientController controller;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IngredientRepository ingredientRepository;

	@MockBean
	MedicineRepository medicineRepository;

	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void test_GetById_ReturnsCorrectStatusAndIngredient_WhenGivenId1() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("paracetamol");
		ingredient.setIngredientId(1L);
		
		when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		this.mockMvc.perform(get("/ingredient/1")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'ingredientId': 1, 'name': 'paracetamol'}")); 
	}
	
	@Test
	public void test_GetById_ReturnsNoDataFoundForId5_WhenGivenId5() throws Exception {
		
		this.mockMvc.perform(get("/ingredient/5")).andDo(print())
		.andExpect(status().isAccepted())
		.andExpect(content().string("No data found for id 5")); 
	}
	
	@Test
	public void test_Add_ReturnsCorrectStatusAndMedicine_WhenGivenMedicine() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("paracetamol");
		ingredient.setIngredientId(1L);
		
		when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(ingredient);
		
		this.mockMvc.perform(post("/ingredient/save").contentType(APPLICATION_JSON_UTF8).content(requestJson))
		.andExpect(status().isCreated())
		.andExpect(content().json("{'ingredientId': 1, 'name': 'paracetamol'}"));
	}
	
	@Test
	public void test_UpdateName_CorrectlyUpdatesName_WhenGivenNameSalicylicAcidAndId1() throws Exception {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("paracetamol");
		ingredient.setIngredientId(1L);
		
		when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
		
		this.mockMvc.perform(post("/ingredient/1/updatename").contentType(APPLICATION_JSON_UTF8).content("new"))
		.andExpect(status().isAccepted())
		.andExpect(content().json("{'ingredientId': 1, 'name': 'new'}"));
	}
	
	@Test
	public void test_UpdateFirstName_ReturnsNoDataForId5_WhenGivenFirstNameJohnAndId5() throws Exception {
		
		this.mockMvc.perform(post("/ingredient/5/updatename").contentType(APPLICATION_JSON_UTF8).content("new"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("No data found for id 5"));
	}
	
}
