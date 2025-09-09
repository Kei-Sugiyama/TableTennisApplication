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
	private  String pairName;	
	private  String rivalName;
	private  String rivalName2;
	private Integer userSet =0;
	private Integer rivalSet = 0;
	private  List<RecordScoresDTO> recordScores;
	
	//JPQL用コンストラクタ
	public RecordDTO(Integer id, LocalDate date,String matchName,String userName,String pairName,
			String rivalName,String rivalName2) {
		this.id = id;
		this.date = date;
		this.matchName = matchName;
		this.userName = userName;
		this.pairName = pairName;
		this.rivalName = rivalName;
		this.rivalName2 = rivalName2;
	}
}
