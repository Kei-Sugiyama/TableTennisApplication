package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRecordsRepository extends JpaRepository<DTO,Integer> {
	//public List<RecordDTO> findByUserId(String userId);
	//public RecordScoresDTO findByMatchId(String matchId);
}
