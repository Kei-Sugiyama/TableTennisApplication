package com.example.demo.form;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRecord2ndForm {
	@Valid
	private List<Set> sets;
	
	@Size(min = 0, max = 250, message = "コメントは250文字以内で入力してください")
	private String comment;
	
	//1stFormのセット数に応じてインスタンスを生成する
	public RegisterRecord2ndForm(List<Set> sets) {
		this.sets = sets;
	}
}
