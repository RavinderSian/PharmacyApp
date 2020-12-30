package com.personal.pharmacy.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.pharmacy.exceptions.NoDataForIdException;
import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.repository.IngredientRepository;

@SpringBootTest
class IngredientServiceImplTest {

	//@Mock never mock the service being tested
	IngredientService ingredientService;
	
	@Mock
	IngredientRepository ingredientRepository;
	
	@Mock
	Ingredient ingredientMock;
	
	@BeforeEach
	public void setUp() throws Exception {
		ingredientService = new IngredientServiceImpl(ingredientRepository);
	}
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		//Act
		ingredientService.save(ingredientMock);
		//Assert
		verify(ingredientRepository, times(1)).save(ingredientMock);
	}
	
	@Test
	public void test_Save_ReturnsCorrectIngredient_WhenGivenIngredientMock() {
		//Assert
		when(ingredientRepository.save(ingredientMock)).thenReturn(ingredientMock);
		//Act
		Ingredient ingredient = ingredientService.save(ingredientMock);
		//Assert
		Assertions.assertEquals(ingredientMock, ingredient);
	}
	
	@Test
	public void test_Delete_Calls_RepositoryDelete_WhenCalled() {
		//Assert
		when(ingredientRepository.save(ingredientMock)).thenReturn(ingredientMock);
		//Act
		ingredientService.save(ingredientMock);
		//Assert
		verify(ingredientRepository, times(1)).save(ingredientMock);
	}
	
	@Test
	public void test_FindAll_Calls_RepositoryFindAll_WhenCalled() {
		//Act
		ingredientService.findAll();
		//Assert
		verify(ingredientRepository, times(1)).findAll();
	}
	
	@Test
	public void test_FindAll_ReturnsEmptyList_WhenGivenNothing() {
		//Arrange
		when(ingredientRepository.findAll()).thenReturn(new ArrayList<>());
		//Act
		List<Ingredient> ingredients = ingredientService.findAll();
		//Assert
		Assertions.assertEquals(0, ingredients.size());
	}
	
	
	@Test
	public void test_FindAll_ReturnsListOfSize1_WhenGivenIngredientMock() {
		//Arrange
		when(ingredientRepository.findAll()).thenReturn(new ArrayList<Ingredient>(Arrays.asList(ingredientMock)));
		//Act
		List<Ingredient> ingredients = ingredientService.findAll();
		//Assert
		Assertions.assertEquals(1, ingredients.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize2_WhenGivenDuplicateIngredientMocks() {
		//Arrange
		when(ingredientRepository.findAll()).thenReturn(new ArrayList<Ingredient>(Arrays.asList(ingredientMock, ingredientMock)));
		//Act
		List<Ingredient> ingredients = ingredientService.findAll();
		//Assert
		Assertions.assertEquals(2, ingredients.size());
	}
	
	@Test
	public void test_FindById_ReturnsIngredientMock_WhenCalledWithId1() {
		//Arrange
		when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredientMock));
		//Act
		Ingredient ingredient = ingredientService.findById(1L);
		//Assert
		Assertions.assertEquals(ingredientMock, ingredient);
	}
	
	@Test
	public void test_FindById_ReturnsNoDataForIdException_WhenCalledWithId1() {

		Assertions.assertThrows(NoDataForIdException.class, () -> ingredientService.findById(1L));
	}
	
	@Test
	public void test_UpdateIngredientName_ReturnsIngredientWithCorrectName_WhenGivenStringNewName() {
		//Arrange
		Ingredient ingredient = new Ingredient();
		ingredient.setName("test");
		//Act
		ingredientService.updateIngredientName(ingredient, "new name");
		//Assert
		Assertions.assertEquals("new name", ingredient.getName());
	}

}
