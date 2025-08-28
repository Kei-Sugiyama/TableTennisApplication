package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class RecordPropertiesDTO {
	private  Integer id;
	private  LocalDate date;
	private  String matchName;
	private  String userName;
	private  String rivalName;
	private Integer userSet =0;
	private Integer rivalSet = 0;
	private String comment;
	private  List<RecordScoresPropertiesDTO> recordScores;
	
	//JPQL用コンストラクタ
	public RecordPropertiesDTO(Integer id, LocalDate date,String matchName,String userName,String rivalName,
			String comment) {
		this.id = id;
		this.date = date;
		this.matchName = matchName;
		this.userName = userName;
		this.rivalName = rivalName;
		this.comment = comment;
	}
}
