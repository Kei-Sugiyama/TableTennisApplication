package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UserRegisterRepository extends JpaRepository<Users,Integer>{
	public Users findByUserId(String userId);
	public Users save(Users loginUser);
}
