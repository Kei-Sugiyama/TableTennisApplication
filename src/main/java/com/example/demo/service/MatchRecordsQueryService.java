package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;

public interface MatchRecordsQueryService {
	public List <RecordDTO> findUserRecords(String userId);
	public RecordPropertiesDTO findUserRecordProperties(Integer matchId);
	
}
