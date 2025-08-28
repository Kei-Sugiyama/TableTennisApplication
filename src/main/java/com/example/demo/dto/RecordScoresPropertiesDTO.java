package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordScoresPropertiesDTO {
	private Integer userScore;
	private Integer rivalScore;
	private String comment;
}
