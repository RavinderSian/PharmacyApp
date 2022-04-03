package com.personal.pharmacy.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "ingredients")
public class Medicine {

	private Long medicineId;
	
	@NotEmpty(message = "Please enter a valid name")
	private String name;
	
	private Integer dosage;
	
	private String duration;
	
	@JsonIgnore
//	@OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@JsonIgnore
//	@ManyToOne
	private Prescription prescription;
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
		//ingredient.setMedicine(this);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		this.ingredients.remove(ingredient);
		//ingredient.setMedicine(null);
	}
    
	//@CreationTimestamp
	private Timestamp createdTime;
	
	//@UpdateTimestamp
	private Timestamp updatedTime;
}
