package com.personal.pharmacy.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.pharmacy.model.Employee;
import com.personal.pharmacy.model.Ingredient;
import com.personal.pharmacy.model.Medicine;
import com.personal.pharmacy.model.Patient;
import com.personal.pharmacy.model.Prescription;
import com.personal.pharmacy.repository.EmployeeRepository;
import com.personal.pharmacy.repository.IngredientRepository;
import com.personal.pharmacy.repository.MedicineRepository;
import com.personal.pharmacy.repository.PatientRepository;
import com.personal.pharmacy.repository.PrescriptionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MappingsBootstrap {
	
//	private final MedicineRepository medicineRepository;
//	private final EmployeeRepository employeeRepository;
//	private final PatientRepository patientRepository;
//	private final IngredientRepository ingredientRepository;
//	private final PrescriptionRepository prescriptionRepository;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		Medicine medicine = new Medicine();
//		medicine.setName("mappingTest");
//		medicineRepository.save(medicine);
//		
//		Ingredient ingredient = new Ingredient();
//		ingredient.setName("testing");
//		ingredientRepository.save(ingredient);
//		medicine.addIngredient(ingredient);
//		medicineRepository.save(medicine);
//		
//		Prescription prescription = new Prescription();
//		prescriptionRepository.save(prescription);
//		prescription.addMedicine(medicine);
//		prescriptionRepository.save(prescription);
//
//		Employee employee = new Employee();
//		employee.setFirstName("testing");
//		employee.setLastName("mapping");
//		employeeRepository.save(employee);
//		employee.addPrescription(prescription);
//		employeeRepository.save(employee);
//		prescriptionRepository.save(prescription);
//
//		
//		Patient patient = new Patient();
//		patient.setFirstName("testing");
//		patient.setLastName("mapping");
//		patientRepository.save(patient);
//		patient.addPrescription(prescription);
//		patientRepository.save(patient);
//		
//	}

}
