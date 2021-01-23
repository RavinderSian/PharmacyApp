package com.personal.pharmacy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDataForIdException extends RuntimeException {

	public NoDataForIdException() {
		super();
	}
	
	public NoDataForIdException(String message) {
		super(message);
	}
	
	public NoDataForIdException(String message, Throwable cause) {
		super(message, cause);
	}
}