package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.dto.RecordScoresPropertiesDTO;
import com.example.demo.repository.MatchRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRecordsQueryServiceImpl implements MatchRecordsQueryService{
	private final MatchRecordsRepository matchRecordsRepository;
	
	@Transactional
	public List<RecordDTO> findUserRecords(String userId){
		//ユーザーIDのすべての試合結果を取得
		//SpringDataJPAは、メソッドの戻り値がListなら、nullを返さないため、nullチェックは不要
		List <RecordDTO> list = matchRecordsRepository.findByUserId(userId);
		
		//スコアと獲得セット数をlistに登録
		for(RecordDTO record:list) {
			Integer matchId = record.getId();
			//各セットのスコアを取得・登録
			List<RecordScoresDTO> scoresList = matchRecordsRepository.findByMatchId(matchId);
			record.setRecordScores(scoresList);
			
			//獲得セット数の計算・登録
			for(RecordScoresDTO scores:scoresList) {
				if(scores.getUserScore()!=null&&scores.getRivalScore()!=null) {
					if((scores.getUserScore()-scores.getRivalScore()>=2)&&(scores.getUserScore()>=11)) {
						record.setUserSet(record.getUserSet() + 1);
					}else if((scores.getRivalScore()-scores.getUserScore()>=2)&&(scores.getRivalScore()>=11)){
						record.setRivalSet(record.getRivalSet() + 1);
					}
				}
			}
		
		}
		return list;
	}
	
	@Transactional
	public RecordPropertiesDTO findUserRecordProperties(Integer matchId){
		 RecordPropertiesDTO recordProperties = matchRecordsRepository.findPropertiesByMatchId(matchId);
		 //各セットのスコアを取得・登録
		 List<RecordScoresPropertiesDTO> scoresList = matchRecordsRepository.findScoresPropertiesByMatchId(matchId);
		 recordProperties.setRecordScores(scoresList);

		 //獲得セット数の計算・登録
		 for(RecordScoresPropertiesDTO scores:scoresList) {
			if(scores.getUserScore()!=null&&scores.getRivalScore()!=null) {
				if((scores.getUserScore()-scores.getRivalScore()>=2)&&(scores.getUserScore()>=11)) {
					recordProperties.setUserSet(recordProperties.getUserSet() + 1);
				}else if((scores.getRivalScore()-scores.getUserScore()>=2)&&(scores.getRivalScore()>=11)){
					recordProperties.setRivalSet(recordProperties.getRivalSet() + 1);
				}
			  }
			}
			
		return recordProperties;
	}
}
