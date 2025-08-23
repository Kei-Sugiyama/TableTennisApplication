package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); //passwordのhash化
	} 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		 http.authorizeHttpRequests(authz -> authz
	            .anyRequest().permitAll() // すべてのリクエストを許可
	        )
	        .formLogin(login -> login
	            .loginPage("/login")               // ログインページのURL
	            .loginProcessingUrl("/login")       // ログイン処理を行うURL
	            .usernameParameter("user_id")       // ユーザーIDのパラメータ名
	            .defaultSuccessUrl("/", true)       // ログイン成功後のリダイレクト先
	            .failureUrl("/login?error=true")    // ログイン失敗後のリダイレクト先
	        );

	    return http.build();
	}
}
