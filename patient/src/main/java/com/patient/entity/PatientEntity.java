package com.patient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "covidpatients")
public class PatientEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patientsId")
	int id;
	
	@Column(name = "patientsName")
	String name;
	
	@Column(name = "patientsAge")
	int age;
	
	@Column(name = "hrctScore")
	int hrctScore;

	public PatientEntity(String name, int age, int hrctScore) {
		super();
		this.name = name;
		this.age = age;
		this.hrctScore = hrctScore;
	}
	
	
}
