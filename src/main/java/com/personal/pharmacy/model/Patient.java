package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
	private List<Prescription> prescriptions = new ArrayList<>();
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
//	public void addPrescription(Prescription prescription) {
//		prescription.setPatient(this);
//		this.prescriptions.add(prescription);
//	}
//	
//	public void removePrescription(Prescription prescription) {
//		prescription.setPatient(null);
//		this.prescriptions.remove(prescription);
//	}
	
	public void setCreatedTime() {
		this.createdTime = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public void setUpdatedTime() {
		this.updatedTime = Timestamp.valueOf(LocalDateTime.now());
	}
}
