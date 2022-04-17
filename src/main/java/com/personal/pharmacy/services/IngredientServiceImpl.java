package com.personal.pharmacy.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@Override
	public Integer delete(Long id) {
		return ingredientRepository.deleteById(id);
		
	}

	@Override
	public Optional<Ingredient> findById(Long id) {
		return ingredientRepository.findById(id).isEmpty()
		? Optional.empty()
		: ingredientRepository.findById(id);
	}
	
	public Integer updateIngredientName(Long id, String name) {
		return ingredientRepository.updateName(id, name);
	}
	
}
