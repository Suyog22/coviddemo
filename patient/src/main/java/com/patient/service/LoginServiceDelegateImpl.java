package com.patient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginServiceDelegateImpl implements LoginServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(LoginServiceDelegateImpl.class);

	@Override
	@CircuitBreaker(name = "AUTH_TOKEN_VALIDATION", fallbackMethod = "fallbackForValidateToken")
	public boolean validateToken(String jwtToken) {
		var headers = new HttpHeaders();
		headers.set("Authorization",jwtToken);
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<Boolean> validateTokenRes =  this.restTemplate.exchange("http://localhost:7000/user/token/validate",
													HttpMethod.GET,
													request, Boolean.class);
		return validateTokenRes.getBody();
	}
	
	public boolean fallbackForValidateToken(String jwtToken,Throwable throwable) {
		logger.info("Invalid token");
		logger.info(jwtToken);
		return false;
	}
	
	@Bean()
	public RestTemplate restTemplate() {
    	return new RestTemplate();
	}

}
