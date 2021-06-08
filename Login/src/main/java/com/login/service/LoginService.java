package com.login.service;

import org.springframework.stereotype.Service;

import com.login.dto.User;

@Service
public class LoginService {

	public String signIn(User user) {
		return "ASDF09" + user;
	}
}
