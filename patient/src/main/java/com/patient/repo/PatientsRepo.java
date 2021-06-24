package com.patient.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.entity.PatientEntity;

public interface PatientsRepo extends JpaRepository<PatientEntity, Integer>{

}
