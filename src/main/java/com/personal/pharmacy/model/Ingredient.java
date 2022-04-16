package com.personal.pharmacy.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Ingredient {

	private Long ingredientId;
	
	@NotEmpty(message = "Please enter a valid name")
	private String name;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
	private Long medicineId;

}
