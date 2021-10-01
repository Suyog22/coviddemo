package com.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.entity.RegisterUserEntity;
import com.login.repo.LoginRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	LoginRepo loginRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)  {
		List<RegisterUserEntity> registredUserList = loginRepo.findByUserName(username);
		if(registredUserList.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		RegisterUserEntity registredUser = registredUserList.get(0);
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
		return new User(registredUser.getUserName(),registredUser.getPassword(),authority);
	}

}
