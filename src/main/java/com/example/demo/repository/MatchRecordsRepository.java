package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.dto.RecordScoresDTO;
import com.example.demo.dto.RecordScoresPropertiesDTO;
import com.example.demo.entity.Matches;

public interface MatchRecordsRepository extends JpaRepository<Matches,Integer> {
	@Query("SELECT new com.example.demo.dto.RecordDTO(m.id, m.date, m.name, u.userName, m.rivalName) "
	            + "FROM Matches m JOIN m.users u WHERE u.userId = :userId")
	public List<RecordDTO> findByUserId(@Param("userId")String userId);
	
	@Query("SELECT new com.example.demo.dto.RecordScoresDTO(s.userScore,s.rivalScore)"
			+"FROM Sets s where s.matchesId = :matchId")
	public List<RecordScoresDTO> findByMatchId(@Param("matchId")Integer matchId);
	
	@Query("SELECT new com.example.demo.dto.RecordPropertiesDTO(m.id, m.date, m.name, u.userName,"
			+ " m.rivalName, m.comment) FROM Matches m JOIN m.users u WHERE u.userId = :userId")
	public List<RecordPropertiesDTO> findPropertiesByUserId(@Param("userId")String userId);
	
	@Query("SELECT new com.example.demo.dto.RecordScoresPropertiesDTO(s.userScore,s.rivalScore,s.comment)"
			+"FROM Sets s where s.matchesId = :matchId")
	public List<RecordScoresPropertiesDTO> findPropertiesByMatchId(@Param("matchId")Integer matchId);
	
}
