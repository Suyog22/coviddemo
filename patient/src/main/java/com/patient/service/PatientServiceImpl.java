package com.patient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dto.Patients;
import com.patient.entity.PatientEntity;
import com.patient.repo.PatientsRepo;
import com.patient.util.PatientsUtil;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientsRepo patientsRepo;

	@Override
	public List<Patients> getPatients() {
		List<PatientEntity> patientsEntityList = patientsRepo.findAll();
		List<Patients> patientList = new ArrayList<>();
		patientsEntityList.stream().forEach(patient -> {
			patientList.add(PatientsUtil.convertPatientEntityToPatient(patient));
		});
		return patientList;
	}

	@Override
	public Patients addPatient(Patients patient) {
		 PatientEntity patientEntity = patientsRepo.save(PatientsUtil.convertPatientToPatientEntity(patient));
		 return PatientsUtil.convertPatientEntityToPatient(patientEntity);
	}

	@Override
	public Patients updatePatient(int id, Patients patient) {
		// TODO Auto-generated method stub
		return null;
	}

}
