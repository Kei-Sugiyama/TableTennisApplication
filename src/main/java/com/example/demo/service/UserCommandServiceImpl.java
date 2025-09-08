package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Users;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public String registerUser(RegisterForm form) {
		Users result = usersRepository.findByUserId(form.getUserId());
		
		if(result==null) {
			Users user = new Users();
			user.setUserId(form.getUserId());
			user.setUserName(form.getUserName());
			user.setHashPassword(passwordEncoder.encode(form.getPassword()));
			user.setRole("GENERAL");
			usersRepository.save(user);
			return null;
		}else {
			return "登録済みのユーザーIDです。";
		}
		
	}
	@Override
	@Transactional
	public RegisterForm bindToRegisterForm(Users user) {
		RegisterForm registerForm = new RegisterForm();
		registerForm.setUserId(user.getUserId());
		registerForm.setUserName(user.getUserName());
		return registerForm;
	}
	
	@Override
	@Transactional
	public String editUser(RegisterForm form,String userId) {
		//userIdに変更があり、かつ、他のユーザーと重複していたらエラー
		if(!(form.getUserId().equals(userId)) && usersRepository.findByUserId(form.getUserId())!=null) {
			return "このユーザーIDは使用済みです。";
		}else{
			Users user = usersRepository.findByUserId(userId);
			user.setUserId(form.getUserId());
			user.setUserName(form.getUserName());
			user.setHashPassword(passwordEncoder.encode(form.getPassword()));
			user.setRole("GENERAL");
			usersRepository.save(user);
			return null;}
	}
}
