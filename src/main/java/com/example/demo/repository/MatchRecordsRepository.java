package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.entity.Matches;

public interface MatchRecordsRepository extends JpaRepository<Matches,Integer> {
	public List<RecordDTO> findByUserId(String userId);
	public List<RecordScoresDTO> findByMatchId(Integer matchId);
}
