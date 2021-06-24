package com.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patients {
	int id;
	String name;
	int age;
	int hrctScore;
	
	public Patients(String name, int age, int hrctScore) {
		super();
		this.name = name;
		this.age = age;
		this.hrctScore = hrctScore;
	}
	
	
}
