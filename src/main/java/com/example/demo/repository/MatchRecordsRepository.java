package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.RecordDTO;
import com.example.demo.dto.RecordPropertiesDTO;
import com.example.demo.entity.Matches;

public interface MatchRecordsRepository extends JpaRepository<Matches,Integer> {
	@Query("SELECT new com.example.demo.dto.RecordDTO(m.id, m.date,m.setsCountId, m.name,u.userName,m.pairName, "
			+ "m.rivalName,m.rivalName2) FROM Matches m JOIN m.users u WHERE u.Id = :usersId ORDER BY m.id Desc")
	public List<RecordDTO> findRecordByUserId(@Param("usersId")Integer usersId);
	
	@Query("SELECT m FROM Matches m WHERE id = :matchId")
	public Matches findByMatchId(@Param("matchId")Integer matchId);
	
	@Query("SELECT new com.example.demo.dto.RecordPropertiesDTO(m.id,m.typeId,m.setsCountId, m.date,"
			+ "m.name, u.userName,m.pairName,m.rivalName,m.rivalName2, m.comment) "
			+ "FROM Matches m JOIN m.users u WHERE m.id = :matchId")
	public RecordPropertiesDTO findPropertiesByMatchId(@Param("matchId")Integer matchId);
	
	public void deleteById(Integer id);

}
