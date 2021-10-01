package com.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public InvalidUserException(String message) {
		super();
		this.message = message;
	}
	
	public InvalidUserException() {
		this.message = "";
	}

	@Override
	public String toString() {
		return "Invalid user details: "+this.message;
	}

}
