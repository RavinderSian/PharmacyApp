package com.personal.pharmacy.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Ingredient {

	private Long ingredientId;
	
	@NotEmpty(message = "Please enter a valid name")
	private String name;
	
//	@CreationTimestamp
//	private Timestamp createdTime;
//	
//	@UpdateTimestamp
//	private Timestamp updatedTime;
//	
//	@ManyToOne
//	private Medicine medicine;
}
