package com.patient.service;

import java.util.List;

import com.patient.dto.Patients;

public interface PatientService {
	
	public List<Patients> getPatients(String jwtToken);
	
	public Patients addPatient(String jwtToken,Patients patient);
	
	public Patients updatePatient(int id, Patients patient);
}
