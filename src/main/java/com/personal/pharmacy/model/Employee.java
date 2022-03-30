package com.personal.pharmacy.model;

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