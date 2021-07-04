package com.patient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dto.Patients;
import com.patient.entity.PatientEntity;
import com.patient.exception.InvalidJsonToken;
import com.patient.exception.InvalidPatientId;
import com.patient.repo.PatientsRepo;
import com.patient.util.PatientsUtil;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientsRepo patientsRepo;
	
	@Autowired
	LoginServiceDelegate loginServiceDelegate;

	@Override
	public List<Patients> getPatients(String jwtToken) {
		if(loginServiceDelegate.validateToken(jwtToken)) {
			List<PatientEntity> patientsEntityList = patientsRepo.findAll();
			List<Patients> patientList = new ArrayList<>();
			patientsEntityList.stream().forEach(patient -> 
				patientList.add(PatientsUtil.convertPatientEntityToPatient(patient))
			);
			return patientList;
		}
		throw new InvalidJsonToken();
	}

	@Override
	public Patients addPatient(String jwtToken, Patients patient) {
		if(loginServiceDelegate.validateToken(jwtToken)) {
			var patientEntity = patientsRepo.save(PatientsUtil.convertPatientToPatientEntity(patient));
			return PatientsUtil.convertPatientEntityToPatient(patientEntity);
		}
		throw new InvalidJsonToken();
	}

	@Override
	public Patients updatePatient(int id, Patients patient, String jwtToken) {
		if(loginServiceDelegate.validateToken(jwtToken)) {
			Optional<PatientEntity> opPatientEntity = patientsRepo.findById(id);
			if(opPatientEntity.isPresent()) {
				var patientEntity = opPatientEntity.get();
				patientEntity.setAge(patient.getAge());
				patientEntity.setHrctScore(patient.getHrctScore());
				patientEntity.setName(patient.getName());
				var updatedPatientEntity = patientsRepo.save(patientEntity);
				return PatientsUtil.convertPatientEntityToPatient(updatedPatientEntity);
			}
			throw new InvalidPatientId();
		}
		throw new InvalidJsonToken();
	}
	
	@Override
	public Boolean deletePatient(int id, String jwtToken) {
		if(loginServiceDelegate.validateToken(jwtToken)) {
			Optional<PatientEntity> opPatientEntity = patientsRepo.findById(id);
			if(opPatientEntity.isPresent()) {
				patientsRepo.deleteById(id);
				return true;
			}
			throw new InvalidPatientId();
		}
		throw new InvalidJsonToken();
	}

}
