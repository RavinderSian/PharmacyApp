package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.exceptions.NoDataForIdException;
import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.repository.IngredientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;

	@Override
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@Override
	public void delete(Ingredient ingredient) {
		ingredientRepository.delete(ingredient);
		
	}

	@Override
	public List<Ingredient> findAll() {
		return (List<Ingredient>) ingredientRepository.findAll();
	}

	@Override
	public Ingredient findById(Long id) {
		Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
		if (!ingredientOptional.isPresent()) {
			throw new NoDataForIdException("No ingredient with id " + id);
		}
		return ingredientOptional.get();
	}
	
	public Ingredient updateIngredientName(Ingredient ingredient, String name) {
		ingredient.setName(name);
		return ingredientRepository.save(ingredient);
	}
	
}
