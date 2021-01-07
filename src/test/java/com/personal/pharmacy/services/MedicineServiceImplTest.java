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

import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.repository.MedicineRepository;

@SpringBootTest
class MedicineServiceImplTest {

	MedicineService medicineService;
	
	@Mock
	MedicineRepository medicineRepository;
	
	@Mock
	Medicine medicineMock;
	
	@BeforeEach
	void setUp() throws Exception {
		medicineService = new MedicineServiceImpl(medicineRepository);
	}

	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		//Act
		medicineService.save(medicineMock);
		//Assert
		verify(medicineRepository, times(1)).save(medicineMock);
		
	}
	
	@Test
	public void test_Save_ReturnsCorrectMedicine_WhenGivenMedicineMock() {
		//Arrange
		when(medicineRepository.save(medicineMock)).thenReturn(medicineMock);
		//Act
		Medicine medicine = medicineService.save(medicineMock);
		//Assert
		Assertions.assertEquals(medicineMock, medicine);
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		//Act
		medicineService.delete(medicineMock);
		//Assert
		verify(medicineRepository, times(1)).delete(medicineMock);
	}
	
	@Test
	public void test_FindAll_Calls_RepositoryFindAll_WhenCalled() {
		//Act
		medicineService.findAll();
		//Assert
		verify(medicineRepository, times(1)).findAll();
	}

	@Test
	public void test_FindAll_ReturnsEmptyList_WhenGivenNothing() {
		//Arrange
		when(medicineRepository.findAll()).thenReturn(new ArrayList<>());
		//Act
		List<Medicine> medicines = medicineService.findAll();
		//Assert
		Assertions.assertEquals(0, medicines.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize1_WhenGivenMedicineMock() {
		//Arrange
		when(medicineRepository.findAll()).thenReturn(new ArrayList<Medicine>(Arrays.asList(medicineMock)));
		//Act
		List<Medicine> medicines = medicineService.findAll();
		//Assert
		Assertions.assertEquals(1, medicines.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize2_WhenGivenDuplicateMedicineMock() {
		//Arrange
		when(medicineRepository.findAll()).thenReturn(new ArrayList<Medicine>(Arrays.asList(medicineMock, medicineMock)));
		//Act
		List<Medicine> medicines = medicineService.findAll();
		//Assert
		Assertions.assertEquals(2, medicines.size());
	}
	
	@Test
	public void test_FindById_ReturnsMedicineMock_WhenCalledWithId1() {
		//Arrange
		when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicineMock));
		//Act
		Optional<Medicine> medicineOptional = medicineService.findById(1L);
		//Assert
		Assertions.assertEquals(medicineOptional.get(), medicineMock);
	}
	
	@Test
	public void test_UpdateName_ReturnsMedicineWithCorrectName_WhenGivenStringName() {
		//Arrange
		Medicine medicine = new Medicine();
		medicine.setName("mock");
		//Act
		medicineService.updateName(medicine, "name");
		//Assert
		Assertions.assertEquals("name", medicine.getName());
	}
}
