package com.personal.pharmacy.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.IngredientRepository;
import com.personal.pharmacy.repository.MedicineRepository;
import com.personal.pharmacy.repository.PrescriptionRepository;

@SpringBootTest
class MedicineServiceImplTest {

	MedicineService medicineService;
	
	@Mock
	MedicineRepository medicineRepository;
	
	@Mock
	IngredientRepository ingredientRepository;
	
	@Mock
	PrescriptionRepository prescriptionRepository;
	
	@Mock
	Medicine medicineMock;
	
	@BeforeEach
	void setUp() throws Exception {
		medicineService = new MedicineServiceImpl(medicineRepository, ingredientRepository, prescriptionRepository);
	}

	@Test
	void test_FindById_ReturnsMedicineMock_WhenCalledWithId1() {
		//Arrange
		when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicineMock));
		//Assert
		Assertions.assertEquals(medicineService.findById(1L).get(), medicineMock);
	}
	
	@Test
	void test_FindById_ReturnsEmptyOptional_WhenCalledWithId5() {
		//Assert
		Assertions.assertTrue(medicineService.findById(5L).isEmpty());
	}
	
	@Test
	void test_GetMedicinesForPrescription_ReturnsCorrectListOfMedicines_WhenGivenId() {
		
		
		Medicine medicine = new Medicine();
		medicine.setMedicineId(1L);
		medicine.setName("test");
		medicine.setDosage(1);
		medicine.setDuration("1 day");
		
		Medicine secondMedicine = new Medicine();
		secondMedicine.setMedicineId(2L);
		secondMedicine.setName("test");
		secondMedicine.setDosage(1);
		secondMedicine.setDuration("1 day");
		
		when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine));
		when(medicineRepository.findById(2L)).thenReturn(Optional.of(secondMedicine));
		
		when(prescriptionRepository.findById(1L)).thenReturn(Optional.of(new Prescription()));
		when(prescriptionRepository.getIdsOfMedicineInPrescription(1L)).thenReturn(Arrays.asList(1L, 2L));

		List<Medicine> medicines = medicineService.getMedicinesForPrescription(1L);
		
		assertThat(medicines.size(), equalTo(2));

	}
	
}
