package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecordDTO {
	private final Integer id;
	private final LocalDate date;
	private final String matchName;
	private final Integer userName;
	private final String rivalName;
	
}
