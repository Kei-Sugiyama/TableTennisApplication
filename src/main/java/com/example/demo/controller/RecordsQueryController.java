package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.service.MatchRecordsQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordsQueryController {
	private final MatchRecordsQueryService matchRecordsQueryService;
	
	@GetMapping("/records")
	public String showRecords(Model model,@AuthenticationPrincipal LoginUserDetails userDetails) {
		model.addAttribute("matchesRecords", matchRecordsQueryService.findUserRecords(userDetails.getUserId()));		
		return "records";
	}
	@GetMapping("/recordsProperties")
	public String showRecordProperties(@RequestParam Integer matchId, Model model) {
	
		model.addAttribute("recordProperties",matchRecordsQueryService.findUserRecordProperties(matchId));//serviceに試合の詳細情報を取得させる
		return "recordsProperties";
	}
}
