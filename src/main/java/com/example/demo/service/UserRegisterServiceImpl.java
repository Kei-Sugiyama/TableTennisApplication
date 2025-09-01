package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Users;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserRegisterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {
	private final UserRegisterRepository userRegisterRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public String registerUser(RegisterForm form) {
		Users result = userRegisterRepository.findByUserId(form.getUserId());
		
		if(result==null) {
			Users loginUser = new Users();
			loginUser.setUserId(form.getUserId());
			loginUser.setUserName(form.getUserName());
			loginUser.setHashPassword(passwordEncoder.encode(form.getPassword()));
			loginUser.setRole(form.getRole());
			userRegisterRepository.save(loginUser);
			return null;
		}else {
			return "登録済みのユーザーIDです。";
		}
		
	}
}
