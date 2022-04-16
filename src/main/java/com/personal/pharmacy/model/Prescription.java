package com.personal.pharmacy.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Prescription {

	private Long prescriptionId;
	
	private Long patientId;
	
	private Long employeeId;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
}
