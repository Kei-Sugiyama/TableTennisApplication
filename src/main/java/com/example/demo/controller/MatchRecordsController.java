package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchRecordsController {
	@GetMapping("/records")
	public String showRecord() {
		return "records";
	}
}
