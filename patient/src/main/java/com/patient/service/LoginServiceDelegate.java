package com.patient.service;

public interface LoginServiceDelegate {
	
	public boolean validateToken(String jwtToken);
}
