package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	private final UsersRepository usersRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) {
		Users loginUser = usersRepository.findByUserId(userId);
		if(loginUser == null) {
			throw new UsernameNotFoundException("Not found user"); 
		}
		else {
			return new LoginUserDetails(loginUser);
		}
	}
}
