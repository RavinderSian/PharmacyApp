package com.personal.pharmacy.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient {

	private Long patientId;
	
	@NotEmpty(message = "Please enter a valid first name")
	private String firstName;
	
	@NotEmpty(message = "Please enter a valid last name")
	private String lastName;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
}
