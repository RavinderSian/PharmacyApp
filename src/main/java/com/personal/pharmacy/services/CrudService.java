package com.personal.pharmacy.services;

import java.util.Optional;

public interface CrudService<T, ID> {

	T save(T t);
	void delete(ID id);
	Optional<T> findById(ID id);
}
