package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchForm {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
	@NotNull
	@Size(min=0, max=30, message="名前は0字以上30字以内で入力してください")
	@Pattern(regexp="^\\S*$", message="対戦相手の名前を入力してください")
	private String name;
	
	@AssertTrue(message="日付と名前、どちらか一つは入力してください")
	private boolean isBothBlank() {
		if(date ==null && name.isEmpty()) {
			return false;
		}
		return true;
	}
}
