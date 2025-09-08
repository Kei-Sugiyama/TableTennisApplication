package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.entity.Users;
import com.example.demo.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserQueryController {
	private final UserQueryService userQueryService;
	
	@GetMapping("/userInformation")
	public String showUserInformation(@AuthenticationPrincipal LoginUserDetails userDetails,Model model) {
		Users user = userQueryService.findUser(userDetails.getLoginId());
		System.out.println(userDetails.getLoginId()+"debug****************************");
		model.addAttribute("user", user);

		return "userInformation";
	}
}	
