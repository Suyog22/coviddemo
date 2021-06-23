package com.login.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.entity.RegisterUserEntity;

public interface LoginRepo extends JpaRepository<RegisterUserEntity, Integer> {
	public List<RegisterUserEntity> findByUserName(String username);
}
