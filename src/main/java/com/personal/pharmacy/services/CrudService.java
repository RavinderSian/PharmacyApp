package com.personal.pharmacy.services;

import java.util.List;

public interface CrudService<T, ID> {

	T save(T t);
	void delete(T t);
	List<T> findAll();
	T findById(ID id);
}
