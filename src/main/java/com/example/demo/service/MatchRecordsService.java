package com.example.demo.service;
import java.util.List;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;

public interface MatchRecordsService {
	public List <RecordDTO> findUserRecords(String userId);
	public List<RecordPropertiesDTO> findUserRecordsProperties(String userId);
}	
