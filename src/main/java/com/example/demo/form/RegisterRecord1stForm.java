package com.example.demo.form;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class RegisterRecord1stForm {
	@NotNull(message = "日付を入力してください")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
	@NotNull
	@Size(min = 0, max = 30, message = "試合名は30文字以内で入力してください")
	private String matchName;
	
	@NotNull
	@Min(value=1, message = "正しい値を入力してください")
	@Max(value=2, message = "正しい値を入力してください")
	private Integer types;
	
	@NotNull
	@Min(value=1, message = "正しい値を入力してください")
	@Max(value=4, message = "正しい値を入力してください")
	private Integer setsCount;
	
	@Size(min = 0, max = 30, message = "ペアの名前は30文字以内で入力してください")
	@Pattern(regexp="^\\S*$", message="ペアの名前を入力してください")
	private String pairName;
	
	@NotNull
	@Size(min = 0, max = 30, message = "対戦相手の名前は30文字以内で入力してください")
	@Pattern(regexp="^\\S+$", message="対戦相手の名前を入力してください")
	private String rivalName;
	
	@Size(min = 0, max = 30, message = "対戦相手の名前は30文字以内で入力してください")
	@Pattern(regexp="^\\S*$", message="対戦相手の名前を入力してください")
	private String rivalName2;
}
