package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController {
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	@PostMapping("/register")
	public String register(@Validated RegisterForm registerForm,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		else {
			//serviceに処理依頼、戻り値がLoginUserなら登録成功。nullなら失敗なのでregisterへ
		}
	}
}
