package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class RecordPropertiesDTO {
	private  Integer id;
	private Integer typeId;
	private Integer setsCountId;
	private  LocalDate date;
	private  String matchName;
	private  String userName;
	private  String rivalName;
	private  String rivalName2;
	private Integer userSet =0;
	private Integer rivalSet = 0;
	private String comment;
	private  List<RecordScoresPropertiesDTO> recordScores;
	
	//JPQL用コンストラクタ
	public RecordPropertiesDTO(Integer id,Integer typeId,Integer setsCountId,LocalDate date,String matchName,
			String userName,String rivalName,String rivalName2,String comment) {
		this.id = id;
		this.typeId = typeId;
		this.setsCountId = setsCountId;
		this.date = date;
		this.matchName = matchName;
		this.userName = userName;
		this.rivalName = rivalName;
		this.rivalName2 = rivalName2;		
		this.comment = comment;
	}
}
