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

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.repository.PatientRepository;

@SpringBootTest
class PatientServiceImplTest {
	
	PatientService patientService;
	
	@Mock
	Patient patientMock;
	
	@Mock
	PatientRepository patientRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		patientService = new PatientServiceImpl(patientRepository);
	}

	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled(){
		//Act
		patientService.save(patientMock);
		//Assert
		verify(patientRepository, times(1)).save(patientMock);
	}
	
	@Test
	public void test_Save_ReturnsCorrectPatient_WhenGivenPatientMock(){
		//Arrange
		when(patientRepository.save(patientMock)).thenReturn(patientMock);
		//Act
		Patient patient = patientService.save(patientMock);
		//Assert
		Assertions.assertEquals(patientMock, patient);
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		//Act
		patientService.delete(patientMock);
		//Assert
		verify(patientRepository, times(1)).delete(patientMock);
	}
	
	@Test
	public void test_FindAll_CallsRepositoryFindAll_WhenCalled() {
		//Act
		patientService.findAll();
		//Assert
		verify(patientRepository, times(1)).findAll();
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize0_WhenGivenNothing() {
		//Arrange
		when(patientRepository.findAll()).thenReturn(new ArrayList<>());
		//Act
		List<Patient> patients = patientService.findAll();
		//Assert
		Assertions.assertEquals(0, patients.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize1_WhenGivenPatientMock() {
		//Arrange
		when(patientRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(patientMock)));
		//Act
		List<Patient> patients = patientService.findAll();
		//Assert
		Assertions.assertEquals(1, patients.size());
	}
	
	@Test
	public void test_FindAll_ReturnsListOfSize2_WhenGivenDuplicatePatientMocks() {
		//Arrange
		when(patientRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(patientMock, patientMock)));
		//Act
		List<Patient> patients = patientService.findAll();
		//Assert
		Assertions.assertEquals(2, patients.size());
	}
	
	@Test
	public void test_FindById_ReturnsPatientMock_WhenCalledWithId1() {
		//Arrange
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patientMock));
		//Act
		Optional<Patient> patientOptional = patientService.findById(1L);
		//Assert
		Assertions.assertEquals(patientOptional.get(), patientMock);
	}
	
	@Test
	public void test_UpdateFirstName_UpdatesFirstNameCorrectly_WhenGivenStringNewName() {
		//Arrange
		Patient patient = new Patient();
		patient.setFirstName("test");
		//Act
		patientService.updateFirstName(patient, "new name");
		//Assert
		Assertions.assertEquals(patient.getFirstName(), "new name");
	}
}
