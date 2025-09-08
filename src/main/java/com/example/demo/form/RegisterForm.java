package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegisterForm {

	@NotNull
	@Pattern(regexp="^\\S{4,20}$" ,message="ユーザーIDを入力してください(4～20字)")
	private String userId;
	@NotNull
	@Pattern(regexp="^\\S{1,20}$",message="ユーザー名を入力してください(1～20字)")
	private String userName;
	@NotNull
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\p{P}).{4,20}$",
			message="英大文字、英小文字、数字、記号を少なくとも1字ずつ使用し、4～20字入力してください。")
	private String password;
}
