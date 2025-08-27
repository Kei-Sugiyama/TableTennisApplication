package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRecordsRepository extends JpaRepository<DTO,Integer> {
	//public List<DTO> findByUserId(String userId);
}
