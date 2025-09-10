package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.form.RegisterForm;

@Controller
public class LoginController {
	@GetMapping({"/","/index"})
	public String showIndex() {
		return "index";
	}
	@GetMapping("/login")
	public String showLogin(RegisterForm registerForm) {
		return "login";
	}
}
