package com.personal.pharmacy.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicineServiceImpl {

//	private final MedicineRepository medicineRepository;
//	
//	@Override
//	public Medicine save(Medicine medicine) {
//		return medicineRepository.save(medicine);
// 	}
//
//	@Override
//	public void delete(Medicine medicine) {
//		medicineRepository.delete(medicine);
//		
//	}
//
//	@Override
//	public List<Medicine> findAll() {
//		return (List<Medicine>) medicineRepository.findAll();
//	}
//
//	@Override
//	public Optional<Medicine> findById(Long id) {
//		
//		return medicineRepository.findById(id).isEmpty()
//		? Optional.empty()
//		: medicineRepository.findById(id);
//	}
//
//	@Override
//	public Medicine findByName(String name) {
//		return medicineRepository.findByName(name);
//	}
//
//	@Override
//	public Medicine updateName(Medicine medicine, String name) {
//		medicine.setName(name);
//		return medicineRepository.save(medicine);
//	}

}
