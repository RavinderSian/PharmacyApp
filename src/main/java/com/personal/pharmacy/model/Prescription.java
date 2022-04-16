package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	
	public void setCreatedTime() {
		this.createdTime = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public void setUpdatedTime() {
		this.updatedTime = Timestamp.valueOf(LocalDateTime.now());
	}
}
