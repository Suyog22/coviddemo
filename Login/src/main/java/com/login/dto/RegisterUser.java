package com.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
	int id;
	String firstName;
	String lastName;
	String userName;
	String password;
	String phone;
}
