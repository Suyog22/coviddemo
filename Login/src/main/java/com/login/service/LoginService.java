package com.login.service;

import com.login.dto.RegisterUser;
import com.login.dto.User;

public interface LoginService {
	public String signIn(User user);
	
	public RegisterUser createNewUser(RegisterUser registerUser);
	
	public Boolean isTokenValid(String jwtToken);
}
