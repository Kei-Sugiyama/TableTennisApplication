package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class RecordDTO {
	private  Integer id;
	private  LocalDate date;
	private  String matchName;
	private  Integer userName;
	private  String rivalName;
	private  List<RecordScoresDTO> recordScores;
}
