package com.example.demo.service;
import com.example.demo.form.RegisterRecord1stForm;
import com.example.demo.form.RegisterRecord2ndForm;

public interface MatchRecordsCommandService {
	public RegisterRecord2ndForm newRecord2ndForm(RegisterRecord1stForm registerRecord1stForm);
	public Integer registerRecord(String userId,RegisterRecord1stForm registerRecord1stForm,
												   RegisterRecord2ndForm registerRecord2ndForm);
	public RegisterRecord1stForm bindResultToForm(Integer matchId,RegisterRecord1stForm registerRecord1stForm);
}	
