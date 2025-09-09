package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.form.RegisterForm;

public interface UserCommandService {
	public String registerUser(RegisterForm registerForm);
	public RegisterForm bindToRegisterForm(Users user);
	public String editUser(RegisterForm registerForm,String loginId);
	public void deleteUser(Integer userId);
}
