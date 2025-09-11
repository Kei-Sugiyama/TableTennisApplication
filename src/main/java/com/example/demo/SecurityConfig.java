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
		return new BCryptPasswordEncoder(); 
	} 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/index","/login","/register").permitAll()
				.anyRequest().authenticated() 
			)
	        .formLogin(login -> login
	            .loginPage("/login")
	            .loginProcessingUrl("/login")
	            .usernameParameter("loginId")
	            .defaultSuccessUrl("/", true)
	            .failureUrl("/login?error=true")
	        )
	        .logout(logout -> logout
	        	.logoutUrl("/logout")
	        	.logoutSuccessUrl("/")
	        	.invalidateHttpSession(true)
	        	.deleteCookies("JSESSIONID")
	        );
		
	    return http.build();
	}
}
