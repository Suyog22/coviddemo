package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dto.Patients;
import com.patient.service.PatientService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Suyog Majgaonkar
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	
	/**
	 * @return List of patients
	 */
	@GetMapping(value="/",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="Returns list of patient", notes="This service is used to display list of patient")
	public ResponseEntity<List<Patients>> getPatients(@RequestHeader("Authorization") String jwtToken) {
		return new ResponseEntity<>(patientService.getPatients(jwtToken),HttpStatus.OK);
	}
	
	
	/**
	 * @param patient
	 * @return Patient
	 */
	@PostMapping(value="/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
	        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="Returns newly created patient", notes="This service is used to add new patient")
	public ResponseEntity<Patients> addPatient(@RequestHeader("Authorization") String jwtToken, @RequestBody Patients patient) {
		return new ResponseEntity<>(patientService.addPatient(jwtToken, patient), HttpStatus.CREATED);
	}

	
	/**
	 * @param jwtToken
	 * @param patient
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
	        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="Returns updated patient", notes="This service is used to update the existing patient")
	public ResponseEntity<Patients> updatePatient(@RequestHeader("Authorization") String jwtToken, @RequestBody Patients patient,
			@PathVariable int id) {
		return new ResponseEntity<>(patientService.updatePatient(id, patient, jwtToken), HttpStatus.OK);
	}
}
