package com.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class InvalidJsonToken extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public InvalidJsonToken(String message) {
		super();
		this.message = message;
	}

	public InvalidJsonToken() {
		this.message = "";
	}

	@Override
	public String toString() {
		return "Invalid Json Token "+this.message;
	}
	
	
	
	
	
}
