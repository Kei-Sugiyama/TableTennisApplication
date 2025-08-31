package com.example.demo.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Set {
	@NotNull
	@Min(value=0, message="0点以上で入力してください")
	@Max(value=40, message="40点以下で入力してください")
	private Integer myScore;
	
	@NotNull
	@Min(value=0, message="0点以上で入力してください")
	@Max(value=40, message="40点以下で入力してください")
	private Integer rivalScore;
	
	@NotNull
	@Size(min=0, max=250, message="250字以下で入力してください")
	private String comment;
}
