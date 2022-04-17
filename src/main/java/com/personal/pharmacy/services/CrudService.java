package com.personal.pharmacy.services;

import java.util.Optional;

public interface CrudService<T, ID> {

	T save(T t);
	Integer delete(ID id);
	Optional<T> findById(ID id);
}
