package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MatchRecords;
import com.example.demo.repository.MatchRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRecordsServiceImpl implements MatchRecordsService{
	private final MatchRecordsRepository matchRecordsRepository;
	
	public List<MatchRecords> findAllRecords(){
		return matchRecordsRepository.findAll();
	}
}
