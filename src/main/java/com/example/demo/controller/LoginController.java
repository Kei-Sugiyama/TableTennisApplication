package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
}
