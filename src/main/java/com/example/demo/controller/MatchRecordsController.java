package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.RegisterRecord1stForm;
import com.example.demo.service.MatchRecordsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchRecordsController {
	private final MatchRecordsService matchRecordsService;
	
	@GetMapping("/records")
	public String showRecords(@RequestParam String userId,Model model) {
		model.addAttribute("matchesRecords", matchRecordsService.findUserRecords(userId));		
		return "records";
	}
	@GetMapping("/recordProperties")
	public String showRecordProperties(@RequestParam Integer matchId, Model model) {
	
		model.addAttribute("recordProperties",matchRecordsService.findUserRecordProperties(matchId));//serviceに試合の詳細情報を取得させる
		return "recordProperties";
	}
	
	@GetMapping("/registerRecord1st")
	public String showRegisterRecord(RegisterRecord1stForm registerRecord1stForm,Model model,HttpSession session) {
		//record2ndから戻ってきた場合
		if(session.getAttribute("registerRecord1stForm")!=null) {
			model.addAttribute("registerRecord1stForm",session.getAttribute("registerRecord1stForm"));
		}
		return "registerRecord1st";
	}
	@PostMapping("/registerRecord2nd")
	public String showRegisterRecordOk(@Validated RegisterRecord1stForm registerRecord1stForm,
			BindingResult bindingResult,Model model,HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "registerRecord1st";
		}
		session.setAttribute("registerRecord1stForm", registerRecord1stForm);
		model.addAttribute("registerRecord2ndForm",matchRecordsService.newRecord2ndForm(registerRecord1stForm));
		return "registerRecord2nd";
	}
}
