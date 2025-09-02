package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.dto.RecordScoresPropertiesDTO;
import com.example.demo.entity.Matches;
import com.example.demo.entity.Sets;
import com.example.demo.entity.Users;
import com.example.demo.form.RegisterRecord1stForm;
import com.example.demo.form.RegisterRecord2ndForm;
import com.example.demo.form.Set;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.repository.MatchRecordsRepository;
import com.example.demo.repository.SetsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRecordsServiceImpl implements MatchRecordsService{
	private final MatchRecordsRepository matchRecordsRepository;
	private final LoginUserRepository loginUserRepository;
	private final SetsRepository setsRepository;
	
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
	public Integer registerRecord(String userId,
			RegisterRecord1stForm registerRecord1stForm,RegisterRecord2ndForm registerRecord2ndForm) {
		
		Users user = loginUserRepository.findByUserId(userId);
		
		//MatchesEntityの生成・DB登録
		Matches match = new Matches();
		match.setDate(registerRecord1stForm.getDate());
		match.setTypeId(registerRecord1stForm.getTypes());
		match.setSetsCountId(registerRecord1stForm.getSetsCount());
		match.setName(registerRecord1stForm.getMatchName());
		match.setRivalName(registerRecord1stForm.getRivalName());
		match.setUsers(user);
		match.setComment(registerRecord2ndForm.getComment());
		
		//match_id取得
		Matches result = matchRecordsRepository.save(match);
		Integer matchId = result.getId();
		
		//SetsEntityの生成・DB登録
		List<Sets> setsList = new ArrayList<>();
		int setNum = 1;
		for(Set set : registerRecord2ndForm.getSets()) {
			Sets setsEntity = new Sets();
			setsEntity.setMatchesId(matchId);
			setsEntity.setSetNum(setNum);
			setsEntity.setUserScore(set.getMyScore());
			setsEntity.setRivalScore(set.getRivalScore());
			setsEntity.setComment(set.getComment());
			setsList.add(setsEntity);
			setNum++;
		}
		setsList.forEach(setsEntity -> setsRepository.save(setsEntity));
		return matchId;
	}

}
