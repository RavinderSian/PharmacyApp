package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Employee {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@NotEmpty(message = "Please enter a valid first name")
//	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty(message = "Please enter a valid last name")
	private String lastName;
	
//	@JsonIgnore
////	@OneToMany(mappedBy = "employee")
//	private List<Prescription> prescriptions = new ArrayList<>();
//	
////	@CreationTimestamp
//	private Timestamp createdTime;
//	
////	@UpdateTimestamp
//	private Timestamp updatedTime;
//	
//	public void addPrescription(Prescription prescription) {
//		prescription.setEmployee(this);
//		this.prescriptions.add(prescription);
//	}
//	
//	public void removePrescription(Prescription prescription) {
//		prescription.setEmployee(null);
//		this.prescriptions.remove(prescription);
//	}
	
	
}