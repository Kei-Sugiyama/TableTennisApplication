package com.example.demo.service;
import java.util.List;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.form.RegisterRecord1stForm;
import com.example.demo.form.RegisterRecord2ndForm;

public interface MatchRecordsService {
	public List <RecordDTO> findUserRecords(String userId);
	public RecordPropertiesDTO findUserRecordProperties(Integer matchId);
	public RegisterRecord2ndForm newRecord2ndForm(RegisterRecord1stForm registerRecord1stForm);
	public Integer registerRecord(String userId,RegisterRecord1stForm registerRecord1stForm,
												   RegisterRecord2ndForm registerRecord2ndForm);
}	
