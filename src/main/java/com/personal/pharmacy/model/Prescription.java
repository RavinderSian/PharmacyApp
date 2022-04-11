package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Prescription {

	private Long prescriptionId;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "prescription", cascade = CascadeType.ALL)
	private List<Medicine> medicines = new ArrayList<>();
	
	@JsonIgnore
	//@ManyToOne
	//@JoinColumn(name = "fk_patient_id")
	private Patient patient;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	private Timestamp createdTime;
	
	private Timestamp updatedTime;
	
	public void addMedicine(Medicine medicine) {
		medicine.setPrescription(this);
		this.medicines.add(medicine);
	}
	
	public void removeMedicine(Medicine medicine) {
		this.medicines.remove(medicine);
		medicine.setPrescription(null);
	}
	
	public void setCreatedTime() {
		this.createdTime = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public void setUpdatedTime() {
		this.updatedTime = Timestamp.valueOf(LocalDateTime.now());
	}
}
