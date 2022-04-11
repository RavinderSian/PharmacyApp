package com.personal.pharmacy.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

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
	void test_FindById_ReturnsEmptyOptional_WhenCalledWithId5() {
		//Assert
		Optional<Patient> patientOptional = patientService.findById(5L);
		Assertions.assertTrue(patientOptional.isEmpty());
	}
	
	@Test
	void test_UpdateFirstName_UpdatesFirstNameCorrectly_WhenGivenStringNewName() {
		when(patientRepository.updateFirstName(1L, "new name")).thenReturn(1);
		//Assert
		assertThat(patientService.updateFirstName(1L, "new name"), equalTo(1));
	}
	
	@Test
	void test_UpdateFirstName_Returns0_WhenIdNotInDb() {
		//Assert
		assertThat(patientService.updateFirstName(1L, "new name"), equalTo(0));
	}
}
