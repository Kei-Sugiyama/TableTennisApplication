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
public class MatchRecordsServiceImpl implements MatchRecordsService{
	private final MatchRecordsRepository matchRecordsRepository;
	
	@Transactional
	public List<RecordDTO> findUserRecords(Integer id){
	//ユーザーIDのすべての試合結果をRepositoryに検索させる
		List <RecordDTO> list = matchRecordsRepository.findByUserId(id);
		for(RecordDTO record:list) {
			Integer matchId = record.getId();
			//各セットのスコアを取得・登録
			List<RecordScoresDTO> scoresList = matchRecordsRepository.findByMatchId(matchId);
			record.setRecordScores(scoresList);
			
			//獲得セット数の計算・登録
			for(RecordScoresDTO scores:scoresList) {
				if((scores.getUserScore()-scores.getRivalScore()>=2)&&(scores.getUserScore()>=11)) {
					record.setUserSet(record.getUserSet() + 1);
				}else if((scores.getRivalScore()-scores.getUserScore()>=2)&&(scores.getRivalScore()>=11)){
					record.setRivalSet(record.getRivalSet() + 1);
				}
			}
		
		}
		return list;
	}

}
