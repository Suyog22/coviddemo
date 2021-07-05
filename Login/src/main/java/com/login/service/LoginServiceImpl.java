package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.login.dto.RegisterUser;
import com.login.dto.User;
import com.login.repo.LoginRepo;
import com.login.util.JwtUtils;
import com.login.util.LoginUtil;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepo loginRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	public String signIn(User user) {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
			return jwtUtils.generateToken(user.getUserName());
		} catch(BadCredentialsException e) {
			throw new BadCredentialsException(user.getUserName());
		}
	}

	public RegisterUser createNewUser(RegisterUser registerUser) {
		 loginRepo.save(LoginUtil.convertRegisterUserToRegisterUserEntity(registerUser));
		 return registerUser;
	}
	
	
	public Boolean isTokenValid(String jwtToken) {
		String userName = jwtUtils.extractUsername(jwtToken);
		var user = userDetailsServiceImpl.loadUserByUsername(userName);
		return jwtUtils.validateToken(jwtToken,user);
	}
}
