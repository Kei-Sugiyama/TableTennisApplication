package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordScoresDTO {
	private Integer userScore;
	private Integer rivalScore;
}
