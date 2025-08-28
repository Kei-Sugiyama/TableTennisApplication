package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;


@Data
public class RecordDTO {
	private  Integer id;
	private  LocalDate date;
	private  String matchName;
	private  String userName;
	private  String rivalName;
	private Integer userSet =0;
	private Integer rivalSet = 0;
	private  List<RecordScoresDTO> recordScores;
	
	//JPQL用コンストラクタ
	public RecordDTO(Integer id, LocalDate date,String matchName,String userName,String rivalName) {
		this.id = id;
		this.date = date;
		this.matchName = matchName;
		this.userName = userName;
		this.rivalName = rivalName;
	}
}
