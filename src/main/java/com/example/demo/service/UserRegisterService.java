package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserRegisterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements RegisterService {
	private final UserRegisterRepository userRegisterRepository;
	
	@Override
	@Transactional
	public String registerUser(RegisterForm form) {
		LoginUser result = userRegisterRepository.findByUserId(form.getUserId());
		
		if(result==null) {
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(form.getUserId());
			loginUser.setUserName(form.getUserName());
			loginUser.setPassword(form.getPassword());
			loginUser.setRole(form.getRole());
			userRegisterRepository.save(loginUser);
			return null;
		}else {
			return "登録済みのユーザーIDです。";
		}
		
	}
}
