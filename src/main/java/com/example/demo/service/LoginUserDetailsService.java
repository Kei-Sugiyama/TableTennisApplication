package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.repository.LoginUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	private final LoginUserRepository loginUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) {
		LoginUser loginUser = loginUserRepository.findByUserId(userId);
		if(loginUser == null) {
			//error
		}
		else {
			//return new UserDetailsの実装クラス(loginUser);
		}
	}
}
