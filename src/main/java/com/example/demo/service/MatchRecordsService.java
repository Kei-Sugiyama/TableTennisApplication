package com.example.demo.service;
import java.util.List;

import com.example.demo.dto.RecordDTO;

public interface MatchRecordsService {
	public List <RecordDTO> findUserRecords(String userId);
}	
