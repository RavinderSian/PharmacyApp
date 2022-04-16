package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

	public void setCreatedTime() {
		this.createdTime = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public void setUpdatedTime() {
		this.updatedTime = Timestamp.valueOf(LocalDateTime.now());
	}
	
}
