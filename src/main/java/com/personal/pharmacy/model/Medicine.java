package com.personal.pharmacy.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Medicine {

	private Long medicineId;
	
	@NotEmpty(message = "Please enter a valid name")
	private String name;
	
	private Integer dosage;
	
	private String duration;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
}
