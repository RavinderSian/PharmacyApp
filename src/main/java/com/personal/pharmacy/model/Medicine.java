package com.personal.pharmacy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "medicines")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineId;
	
	@Column(name = "medicine_name")
	private String name;
	
	@Column(name = "strength_mg")
	private Integer strength;
	
	@Column(name = "dosage")
	private Integer dosage;
	
	@Column(name = "duration")
	private String duration;
	
	@ManyToOne
	@JoinColumn(name = "fk_prescription_id")
	private Prescription prescription;
	
	
}
