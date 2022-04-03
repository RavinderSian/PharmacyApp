package com.personal.pharmacy.repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {

	T save(T t);
	void deleteById(ID id);
	Optional<T> findById(ID id);
}
