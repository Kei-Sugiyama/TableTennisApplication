package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.RegisterForm;
import com.example.demo.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegisterController {
	private final RegisterService registerService;
	
	@GetMapping("/register")
	public String showRegister(RegisterForm registerForm) {
		return "register";
	}
	@PostMapping("/register")
	public String register(@Validated RegisterForm registerForm,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		else {
			//serviceへの処理依頼。失敗なら戻り値がエラーメッセージ。登録成功なら戻り値null。
			String message = registerService.registerUser(registerForm);
			if(message!=null) {
				model.addAttribute("message", message);
				return "register";
			}else {
				return "registerOk";
			}
		}
	}
}
