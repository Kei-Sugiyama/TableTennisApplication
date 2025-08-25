package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.MatchRecordsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchRecordsController {
	private final MatchRecordsService matchRecordsService;
	
	@GetMapping("/records")
	public String showRecords(Model model) {
		model.addAttribute("matchRecords", matchRecordsService.findAllRecords());
		return "records";
	}
	@GetMapping("/registerRecord")
	public String showRegisterRecord() {
		return "registerRecord";
	}
}
