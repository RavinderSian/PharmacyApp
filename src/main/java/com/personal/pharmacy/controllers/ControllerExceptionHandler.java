package com.personal.pharmacy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.personal.pharmacy.exceptions.NoDataForIdException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(NoDataForIdException.class)	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoDataForId(Exception exception){
		String message = exception.getMessage();
		return message;
	}
	
}
