package com.personal.pharmacy.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

	void save(T t);
	void delete(ID id);
	List<T> findAll();
	Optional<T> findById(ID id);
}
