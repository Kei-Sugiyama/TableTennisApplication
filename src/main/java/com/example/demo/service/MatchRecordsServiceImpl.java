package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.dto.RecordScoresPropertiesDTO;
import com.example.demo.form.RegisterRecord1stForm;
import com.example.demo.form.RegisterRecord2ndForm;
import com.example.demo.form.Set;
import com.example.demo.repository.MatchRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRecordsServiceImpl implements MatchRecordsService{
	private final MatchRecordsRepository matchRecordsRepository;
	
	@Transactional
	public List<RecordDTO> findUserRecords(String userId){
	//ユーザーIDのすべての試合結果をRepositoryに検索させる
		List <RecordDTO> list = matchRecordsRepository.findByUserId(userId);
	
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
	
	@Transactional
	public RecordPropertiesDTO findUserRecordProperties(Integer matchId){
		 RecordPropertiesDTO recordProperties = matchRecordsRepository.findPropertiesBymatchId(matchId);
		 //各セットのスコアを取得・登録
		 List<RecordScoresPropertiesDTO> scoresList = matchRecordsRepository.findPropertiesByMatchId(matchId);
		 recordProperties.setRecordScores(scoresList);

		 //獲得セット数の計算・登録
		 for(RecordScoresPropertiesDTO scores:scoresList) {
			if((scores.getUserScore()-scores.getRivalScore()>=2)&&(scores.getUserScore()>=11)) {
					recordProperties.setUserSet(recordProperties.getUserSet() + 1);
				}else if((scores.getRivalScore()-scores.getUserScore()>=2)&&(scores.getRivalScore()>=11)){
					recordProperties.setRivalSet(recordProperties.getRivalSet() + 1);
				}
			}
			
		return recordProperties;
	}
	public RegisterRecord2ndForm newRecord2ndForm(RegisterRecord1stForm registerRecord1stForm) {
		Integer setsId = registerRecord1stForm.getSetsCount();
		int setMatch = 0; 
		switch(setsId) {
			case 1 -> setMatch = 1;//1セットマッチ
			case 2 -> setMatch = 3;//3セットマッチ
			case 3 -> setMatch = 5;//5セットマッチ
			case 4 -> setMatch = 7;//7セットマッチ
		}
		List <Set> sets = new ArrayList<>();
		for(int i = 0; i < setMatch;i++) {
			 sets.add(new Set());
		}			
		return new RegisterRecord2ndForm(sets) ;
	}

}
