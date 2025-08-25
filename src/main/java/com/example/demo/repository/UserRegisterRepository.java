package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginUser;

public interface UserRegisterRepository extends JpaRepository<LoginUser,Integer>{
	public LoginUser findByUserId(String userId);
	public LoginUser save(LoginUser loginUser);
}
