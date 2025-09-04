package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.dto.RecordScoresPropertiesDTO;
import com.example.demo.entity.Sets;

public interface SetsRepository extends JpaRepository<Sets,Integer> {
	@Query("SELECT new com.example.demo.dto.RecordScoresDTO(s.userScore,s.rivalScore)"
			+"FROM Sets s where s.matchesId = :matchId")
	public List<RecordScoresDTO> findRecordScoresDtoByMatchId(@Param("matchId")Integer matchId);
	
	@Query("SELECT new com.example.demo.dto.RecordScoresPropertiesDTO(s.userScore,s.rivalScore,s.comment)"
			+"FROM Sets s where s.matchesId = :matchId")
	public List<RecordScoresPropertiesDTO> findScoresPropertiesByMatchId(@Param("matchId")Integer matchId);
	
	public void deleteByMatchesId(Integer matchId);
}	
