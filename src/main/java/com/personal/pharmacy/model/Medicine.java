package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "medicines")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineId;
	
	@Column(name = "name")
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
	
	@OneToMany
	private Set<Ingredient> ingredients = new HashSet<>();
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		this.ingredients.remove(ingredient);
	}
	
	@CreationTimestamp
	private Timestamp createdTime;
	
	@UpdateTimestamp
	private Timestamp updatedTime;
}
