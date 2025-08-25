package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MatchRecords;

public interface MatchRecordsRepository extends JpaRepository<MatchRecords,Integer> {
	public List<MatchRecords> findAll();
}
