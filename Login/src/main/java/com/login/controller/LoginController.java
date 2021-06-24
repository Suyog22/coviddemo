package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.dto.RegisterUser;
import com.login.dto.User;
import com.login.service.LoginService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Suyog Majgaonkar
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/user")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	/**
	 * @param user
	 * @returns the jwt web token if user is authenticated
	 */
	@PostMapping(value="/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
	        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="Returns auth token", notes="This service returns the auth token")
	public ResponseEntity<String> signIn(@RequestBody User user) {
		String jwtToken = loginService.signIn(user);
		return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	}

	@PostMapping(value="/createuser", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
       	 produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="Returns newly created user", notes="This service is used to create new user")
	public ResponseEntity<RegisterUser> createNewUser(@RequestBody RegisterUser registerUser) {
		RegisterUser createdUser = loginService.createNewUser(registerUser);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}
	
	@GetMapping(value="/token/validate",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> isTokenValid(@RequestHeader("Authorization") String jwtToken) {
		jwtToken = jwtToken.substring(jwtToken.indexOf(' '));
		boolean isTokenValid = loginService.isTokenValid(jwtToken);
		return new ResponseEntity<>(isTokenValid,HttpStatus.OK);
	}
}
