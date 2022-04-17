package com.personal.pharmacy.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Prescription {

	private Long prescriptionId;
	
	@NotNull(message = "Please enter a valid patient id")
	private Long patientId;
	
	@NotNull(message = "Please enter a valid employee id")
	private Long employeeId;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
}
