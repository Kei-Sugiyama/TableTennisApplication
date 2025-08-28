package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.repository.MatchRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRecordsServiceImpl {
	private final MatchRecordsRepository matchRecordsRepository;
	
	@Transactional
	public List<RecordDTO> findUserRecords(String userId){
	//ユーザーIDのすべての試合結果をRepositoryに検索させる
		List <RecordDTO> list = matchRecordsRepository.findByUserId(userId);
		for(RecordDTO record:list) {
			Integer matchId = record.getId();
			List<RecordScoresDTO> scoresList = matchRecordsRepository.findByMatchId(matchId);
			record.setRecordScores(scoresList);
		}
		return list;
	}

}
