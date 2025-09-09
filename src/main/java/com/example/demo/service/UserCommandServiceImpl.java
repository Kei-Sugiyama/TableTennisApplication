package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RecordDTO;
import com.example.demo.entity.Users;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.MatchRecordsRepository;
import com.example.demo.repository.SetsRepository;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	private final MatchRecordsRepository matchRecordsRepository;
	private final SetsRepository setsRecordsRepository;
	
	
	@Override
	@Transactional
	public String registerUser(RegisterForm form) {
		Users result = usersRepository.findByLoginId(form.getLoginId());
		
		if(result==null) {
			Users user = new Users();
			user.setLoginId(form.getLoginId());
			user.setUserName(form.getUserName());
			user.setHashPassword(passwordEncoder.encode(form.getPassword()));
			user.setRole("GENERAL");
			usersRepository.save(user);
			return null;
		}else {
			return "登録済みのユーザーIDです。";
		}
		
	}
	@Override
	@Transactional
	public RegisterForm bindToRegisterForm(Users user) {
		RegisterForm registerForm = new RegisterForm();
		registerForm.setLoginId(user.getLoginId());
		registerForm.setUserName(user.getUserName());
		return registerForm;
	}
	
	@Override
	@Transactional
	public String editUser(RegisterForm form,String loginId) {
		//userIdに変更があり、かつ、他のユーザーと重複していたらエラー
		if(!(form.getLoginId().equals(loginId)) && usersRepository.findByLoginId(form.getLoginId())!=null) {
			return "このユーザーIDは使用済みです。";
		}else{
			Users user = usersRepository.findByLoginId(loginId);
			user.setLoginId(form.getLoginId());
			user.setUserName(form.getUserName());
			user.setHashPassword(passwordEncoder.encode(form.getPassword()));
			user.setRole("GENERAL");
			usersRepository.save(user);
			return null;}
	}
	
	@Override
	@Transactional
	public void deleteUser(Integer userId) {
		//userIdを外部キーで参照するmatchesと、matchesを外部キーで参照するsetsを削除
		List<RecordDTO> list = matchRecordsRepository.findRecordDByUserId(userId);
		List<Integer> matchIdList = list.stream().map(elm->elm.getId()).toList();
		matchIdList.forEach(matchId -> setsRecordsRepository.deleteByMatchesId(matchId));
		matchIdList.forEach(matchId -> matchRecordsRepository.deleteById(matchId));
		
		//userの削除
		usersRepository.deleteById(userId);
	}
}
