package com.login.util;

import com.login.dto.RegisterUser;
import com.login.entity.RegisterUserEntity;

public class LoginUtil {
	
	public static RegisterUserEntity convertRegisterUserToRegisterUserEntity(RegisterUser registerUser) {
		return new RegisterUserEntity(
				registerUser.getFirstName(),
				registerUser.getLastName(),
				registerUser.getUserName(),
				registerUser.getPassword(),
				registerUser.getPhone()
				);
	}
}
