package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{
	private final UsersRepository usersRepository;
	
	public Users findUser(String loginId) {
		Users user = usersRepository.findByLoginId(loginId);
		return user;
	}
}
