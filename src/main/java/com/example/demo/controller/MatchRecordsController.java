package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.MatchRecordsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchRecordsController {
	private final MatchRecordsService matchRecordsService;
	
	@GetMapping("/records")
	public String showRecords(@RequestParam String userId,Model model) {
		//model.addAttribute("matchRecords", matchRecordsService.findUserRecords(userId));
		return "records";
	}
	@GetMapping("/registerRecord")
	public String showRegisterRecord() {
		return "registerRecord";
	}
}
