package com.example.demo.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.entity.Users;
import com.example.demo.form.RegisterForm;
import com.example.demo.service.UserCommandService;
import com.example.demo.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserCommandController {
	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;
	
	@GetMapping("/userEdit")
	public String showEditUser(@AuthenticationPrincipal LoginUserDetails userDetails,Model model) {
		String userId = userDetails.getUserId();
		Users user = userQueryService.findUser(userId);
		//userとregisterFormを紐づけて表示
		RegisterForm registerForm = userCommandService.bindToRegisterForm(user);
		model.addAttribute("registerForm", registerForm);
		return "userEdit";
	}
	@PostMapping("/userEditOut")
	public String editUser(@AuthenticationPrincipal LoginUserDetails userDetails,
			@Validated RegisterForm registerForm,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "userEdit";
		}else {
			String userId = userDetails.getUserId();
			String error = userCommandService.editUser(registerForm,userId);
			if(error!=null) {
				model.addAttribute("error",error);
				return "userEdit";
			}
			model.addAttribute("user",userQueryService.findUser(registerForm.getUserId()));
			return "userEditOk";
		}
	}
	
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
			String message = userCommandService.registerUser(registerForm);
			if(message!=null) {
				model.addAttribute("message", message);
				return "register";
			}else {
				return "registerOk";
			}
		}
	}
}
