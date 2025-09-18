package com.example.demo.controller;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.entity.Users;
import com.example.demo.form.RegisterForm;
import com.example.demo.service.LoginUserDetailsService;
import com.example.demo.service.UserCommandService;
import com.example.demo.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserCommandController {
	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;
	private final LoginUserDetailsService loginUserDetailsService;
	
	@GetMapping("/userEdit")
	public String showEditUser(@AuthenticationPrincipal LoginUserDetails userDetails,Model model) {
		String loginId = userDetails.getLoginId();
		Users user = userQueryService.findUser(loginId);
		//userとregisterFormを紐づけて表示
		RegisterForm registerForm = userCommandService.bindToRegisterForm(user);
		model.addAttribute("registerForm", registerForm);
		return "userEdit";
	}
	@PostMapping("/userEditOut")
	public String editUser(@AuthenticationPrincipal LoginUserDetails userDetails,
			@Validated RegisterForm registerForm,BindingResult bindingResult,Model model,
			RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "userEdit";
		}else {
			String loginId = userDetails.getLoginId();
			String error = userCommandService.editUser(registerForm,loginId);
			if(error!=null) {//他人のログインIDと被っていたら
				model.addAttribute("error",error);
				return "userEdit";
			}
			Users user = userQueryService.findUser(registerForm.getLoginId());
			redirectAttributes.addFlashAttribute("user",user);
			
			//Authenticationオブジェクトの中のuserDetailsも更新する
			UserDetails newUserDetails = loginUserDetailsService.loadUserByUsername(user.getLoginId());
			//セッションにキャッシュされた古い情報を新しい情報に置き換え
			Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		    UsernamePasswordAuthenticationToken newAuth = 
		    		new UsernamePasswordAuthenticationToken(newUserDetails, currentAuth.getCredentials(), 
		    				newUserDetails.getAuthorities());
		    //SecurityContextHolderに新しいAuthenticationオブジェクトをセットし、セッションを更新する
		    SecurityContextHolder.getContext().setAuthentication(newAuth);
		    
			
			return "redirect:/userEditOk";
		}
	}
	@GetMapping("/userEditOk")
	public String editUser() {
		return "userEditOK";
	}
	
	@GetMapping("/register")
	public String showRegister(RegisterForm registerForm) {
		return "register";
	}
	@PostMapping("/registerOut")
	public String register(@Validated RegisterForm registerForm,BindingResult bindingResult,Model model,
			RedirectAttributes redirectAttributes) {
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
				//リダイレクト先のみで参照できるオブジェクト
				redirectAttributes.addFlashAttribute("registerForm",registerForm);
				return "redirect:/registerOk";
			}
		}
	}
	@GetMapping("/registerOk")
	public String register() {
		return "registerOk";
	}
	
	
	@GetMapping("/userDelete")
	public String showDeleteUser(@AuthenticationPrincipal LoginUserDetails userDetails,Model model) {
		Users user =  userQueryService.findUser(userDetails.getLoginId());
		model.addAttribute("user",user);
		return "userDelete";
	}
	@GetMapping("/userDeleteOut")
	public String deleteUser(@AuthenticationPrincipal LoginUserDetails userDetails) {
		userCommandService.deleteUser(userDetails.getId());
		return "redirect:/userDeleteOk";
	}
}
