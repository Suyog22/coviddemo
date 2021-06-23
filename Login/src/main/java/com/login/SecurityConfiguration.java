package com.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.login.service.LoginService;
import com.login.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user","user/authenticate","user/createuser").permitAll()
		.and()
		.formLogin();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
