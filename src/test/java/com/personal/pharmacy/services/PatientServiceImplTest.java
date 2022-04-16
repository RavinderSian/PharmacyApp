package com.personal.pharmacy.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.repository.PatientRepository;
import com.personal.pharmacy.repository.PrescriptionRepository;

@SpringBootTest
class PatientServiceImplTest {
	
	PatientService patientService;
	
	@Mock
	Patient patientMock;
	
	@Mock
	PatientRepository patientRepository;
	
	@Mock
	PrescriptionRepository prescriptionRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		patientService = new PatientServiceImpl(patientRepository, prescriptionRepository);
	}

	@Test
	void test_FindById_ReturnsEmptyOptional_WhenCalledWithId5() {
		//Assert
		Optional<Patient> patientOptional = patientService.findById(5L);
		Assertions.assertTrue(patientOptional.isEmpty());
	}
	
}
