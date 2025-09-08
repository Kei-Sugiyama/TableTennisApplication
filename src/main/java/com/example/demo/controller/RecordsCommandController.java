package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.details.LoginUserDetails;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.form.RegisterRecord1stForm;
import com.example.demo.form.RegisterRecord2ndForm;
import com.example.demo.service.MatchRecordsCommandService;
import com.example.demo.service.MatchRecordsQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordsCommandController {
	private final MatchRecordsCommandService matchRecordsCommandService;
	private final MatchRecordsQueryService matchRecordsQueryService;
	
	@GetMapping("/registerRecord1st")
	public String showRegisterRecord(RegisterRecord1stForm registerRecord1stForm,Model model,HttpSession session) {
		//record2ndから戻ってきた場合に、modelに1stformオブジェクトを再格納
		if(session.getAttribute("registerRecord1stForm")!=null) {
			model.addAttribute("registerRecord1stForm",session.getAttribute("registerRecord1stForm"));
		}
		return "registerRecord1st";
	}
	@PostMapping("/registerRecord2nd")
	public String showRegisterRecord2nd(@Validated RegisterRecord1stForm registerRecord1stForm,
			BindingResult bindingResult,Model model,HttpSession session) {
		if(bindingResult.hasErrors()) {
			return "registerRecord1st";
		}
		session.setAttribute("registerRecord1stForm", registerRecord1stForm);
		//1stFormで選択したセット数に合わせ、2ndFormを生成する
		model.addAttribute("registerRecord2ndForm",matchRecordsCommandService.newRecord2ndForm(registerRecord1stForm));
		return "registerRecord2nd";
	}
	
	@PostMapping("/registerRecordOut")
	public String showRegisterRecordOk(@Validated RegisterRecord2ndForm registerRecord2ndForm,BindingResult bindingResult
				,HttpSession session,Model model,@AuthenticationPrincipal LoginUserDetails userDetails) {
		if(bindingResult.hasErrors()) {
			return "registerRecord2nd";
		}
		//試合結果登録とmatchId取得
		Integer matchId = matchRecordsCommandService.registerRecord(userDetails.getId(),
				(RegisterRecord1stForm)session.getAttribute("registerRecord1stForm"),registerRecord2ndForm);
		return "redirect:/registerRecordOk?matchId=" + matchId;
		
	}
	@GetMapping("/registerRecordOk")
	public  String showRegisterRecordOk(@RequestParam Integer matchId,Model model) {
		model.addAttribute("recordProperties",matchRecordsQueryService.findUserRecordProperties(matchId));
		return "registerRecordOk";
	}
	
	@GetMapping("/editRecord1st")
	public String showEditRecord1st(@RequestParam Integer matchId,Model model,RegisterRecord1stForm registerRecord1stForm) {
		RecordPropertiesDTO dto = matchRecordsQueryService.findUserRecordProperties(matchId);
		//検索結果をformクラスに紐づけてth:valueを利用する
		RegisterRecord1stForm form = matchRecordsCommandService.bindResultTo1stForm(dto,registerRecord1stForm);
		model.addAttribute("registerRecord1stform", form);
		model.addAttribute("matchId",matchId);
		return "editRecord1st";
	}
	@PostMapping("/editRecord2nd")
	public String showEditRecord2nd (@RequestParam Integer matchId,@Validated RegisterRecord1stForm registerRecord1stForm, 
				BindingResult bindingResult,HttpSession session,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("matchId",matchId);
			return "editRecord1st";
		}
		session.setAttribute("registerRecord1stForm", registerRecord1stForm);
		
		//1stFormで選択したセット数に合わせ、2ndFormを生成する
		RegisterRecord2ndForm registerRecord2ndform = matchRecordsCommandService.newRecord2ndForm(registerRecord1stForm);
		
		RecordPropertiesDTO dto =  matchRecordsQueryService.findUserRecordProperties(matchId);
		registerRecord2ndform = matchRecordsCommandService.bindResultTo2ndForm(dto,registerRecord2ndform);
		
		model.addAttribute("matchId",matchId);
		model.addAttribute("registerRecord2ndForm", registerRecord2ndform);
		return "editRecord2nd";
	}
	@PostMapping("/editRecordOut")
	public String editRecordOk(@Validated RegisterRecord2ndForm registerRecord2ndForm,BindingResult bindingResult
			,HttpSession session,Model model,@RequestParam Integer matchId) {
		if(bindingResult.hasErrors()) {
			return "registerRecord2nd";
		}
		matchRecordsCommandService.updateRecord(matchId,(RegisterRecord1stForm)session.getAttribute("registerRecord1stForm")
				,registerRecord2ndForm);
		
	return "redirect:/editRecordOk?matchId=" + matchId;
	}
	@GetMapping("/editRecordOk")
	public String editRecordOk(@RequestParam Integer matchId, Model model) {
		model.addAttribute("recordProperties",matchRecordsQueryService.findUserRecordProperties(matchId));
		return "editRecordOk";
	}
	
	@GetMapping("/deleteRecord")
	public String showDeleteRecord (@RequestParam Integer matchId,Model model) {
		model.addAttribute("recordProperties", matchRecordsQueryService.findUserRecordProperties(matchId));
		return "deleteRecord";
	}
	@GetMapping("/deleteRecordOut")
	public String deleteRecord(@RequestParam Integer matchId) {
		matchRecordsCommandService.deleteRecord(matchId);
		return "deleteRecordOk";
	}
}
