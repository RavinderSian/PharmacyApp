package com.personal.pharmacy.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Employee {

	private Long employeeId;
	
	@NotNull(message = "Please enter a valid first name")
	@NotEmpty(message = "Please enter a valid first name")
	private String firstName;
	
	@NotNull(message = "Please enter a valid last name")
	@NotEmpty(message = "Please enter a valid last name")
	private String lastName;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
}