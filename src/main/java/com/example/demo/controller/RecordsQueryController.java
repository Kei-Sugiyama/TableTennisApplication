package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.form.SearchForm;
import com.example.demo.service.MatchRecordsQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordsQueryController {
	private final MatchRecordsQueryService matchRecordsQueryService;
	
	@GetMapping("/records")
	public String showRecords(Model model,@AuthenticationPrincipal LoginUserDetails userDetails) {
		model.addAttribute("matchesRecords", matchRecordsQueryService.findUserRecords(userDetails.getId()));
		return "records";
	}
	@GetMapping("/recordsProperties")
	public String showRecordProperties(@RequestParam Integer matchId, Model model) {
	
		model.addAttribute("recordProperties",matchRecordsQueryService.findUserRecordProperties(matchId));
		return "recordsProperties";
	}
	@GetMapping("/searchRecords")
	public String showSearchRecords(Model model) {
		model.addAttribute("searchForm",new SearchForm());
		return "searchRecords";
	}
	@PostMapping("/searchRecordsOut")
	public String showSerachRecordsOut(@Validated SearchForm searchForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			//debug
			System.out.println("debug***********************************************"
			+ searchForm.getName().isEmpty() + searchForm.getDate());
			return "searchRecords";
		}
		
		return "searchRecordsResult";
	}
}
