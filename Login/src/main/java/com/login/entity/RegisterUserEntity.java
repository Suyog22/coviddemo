package com.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "registered_user")
public class RegisterUserEntity {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String firstName;
	String lastName;
	String userName;
	String password;
	String phone;
	
	public RegisterUserEntity(String firstName, String lastName, String userName, String password, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}
	
	
}
