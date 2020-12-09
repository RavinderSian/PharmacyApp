package com.personal.pharmacy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "prescriptions")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prescriptionId;
	
	@OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY)
	private List<Medicine> medicines = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "fk_patient_id")
	private Patient patient;
	
	@OneToOne
	private Employee employee;
}
