package com.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidPatientId extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	final String message;

	public InvalidPatientId(String message) {
		super();
		this.message = message;
	}
	
	public InvalidPatientId() {
		this.message = "";
	}

	@Override
	public String toString() {
		return "Invalid Patient Id"+message;
	}
	
	
}
