package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RecordPropertiesDTO;
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
public class MatchRecordsCommandServiceImpl implements MatchRecordsCommandService{
	private final MatchRecordsRepository matchRecordsRepository;
	private final LoginUserRepository loginUserRepository;
	private final SetsRepository setsRepository;

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
	@Transactional
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
		int setNum = 1;//1セット目から逐次入力
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
	
	@Transactional
	public Integer updateRecord(Integer matchId,
			RegisterRecord1stForm registerRecord1stForm,RegisterRecord2ndForm registerRecord2ndForm) {
		
		//matchIdで検索
		Matches match = matchRecordsRepository.findByMatchId(matchId);
		
		//MatchesEntityの修正
		match.setDate(registerRecord1stForm.getDate());
		match.setTypeId(registerRecord1stForm.getTypes());
		match.setSetsCountId(registerRecord1stForm.getSetsCount());
		match.setName(registerRecord1stForm.getMatchName());
		match.setRivalName(registerRecord1stForm.getRivalName());
		match.setComment(registerRecord2ndForm.getComment());
		
		//DB更新（matchIdがあるため、updateになる）
		matchRecordsRepository.save(match);

		
		//更新前のsetsを削除
		setsRepository.deleteByMatchesId(matchId);
		
		//SetsEntityの生成・DB登録
		List<Sets> setsEntityList = new ArrayList<>();
		int setNum = 1;//1セット目から逐次入力
		for(Set set : registerRecord2ndForm.getSets()) {
			Sets setsEntity = new Sets();
			setsEntity.setMatchesId(matchId);
			setsEntity.setSetNum(setNum);
			setsEntity.setUserScore(set.getMyScore());
			setsEntity.setRivalScore(set.getRivalScore());
			setsEntity.setComment(set.getComment());
			setsEntityList.add(setsEntity);
			setNum++;
		}
		setsEntityList.forEach(setsEntity -> setsRepository.save(setsEntity));
		return matchId;
	}
	
	public RegisterRecord1stForm bindResultTo1stForm(RecordPropertiesDTO dto,RegisterRecord1stForm registerRecord1stForm) {
		registerRecord1stForm.setDate(dto.getDate());
		registerRecord1stForm.setMatchName(dto.getMatchName());
		registerRecord1stForm.setTypes(dto.getTypeId());
		registerRecord1stForm.setSetsCount(dto.getSetsCountId());
		registerRecord1stForm.setRivalName(dto.getRivalName());
		
		return registerRecord1stForm;
		
	}
	
	public RegisterRecord2ndForm bindResultTo2ndForm(RecordPropertiesDTO dto,RegisterRecord2ndForm registerRecord2ndForm) {
		
		//2ndFormの各セットにDTOの各セットのスコア・コメントを紐づけ
		int count = 0;
			for(Set set :registerRecord2ndForm.getSets()) {
			set.setMyScore(dto.getRecordScores().get(count).getUserScore());
			set.setRivalScore(dto.getRecordScores().get(count).getRivalScore());
			set.setComment(dto.getRecordScores().get(count).getComment());
			count++;
		}
		registerRecord2ndForm.setComment(dto.getComment());
		return registerRecord2ndForm;
	}

}
