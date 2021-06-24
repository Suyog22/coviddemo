package com.patient.util;

import com.patient.dto.Patients;
import com.patient.entity.PatientEntity;

public class PatientsUtil {
	
	private PatientsUtil() { }
	
	public static PatientEntity convertPatientToPatientEntity(Patients patient) {
		return new PatientEntity(patient.getName(),patient.getAge(),patient.getHrctScore());
	}
	
	public static Patients convertPatientEntityToPatient(PatientEntity patientEntity) {
		return new Patients(patientEntity.getId(), patientEntity.getName(),
				patientEntity.getAge(), patientEntity.getHrctScore());
	}
}
