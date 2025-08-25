package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.entity.LoginUser;
import com.example.demo.repository.LoginUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	private final LoginUserRepository loginUserRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) {
		LoginUser loginUser = loginUserRepository.findByUserId(userId);
		if(loginUser == null) {
			throw new UsernameNotFoundException("Not found user"); 
		}
		else {
			return new LoginUserDetails(loginUser);
		}
	}
}
