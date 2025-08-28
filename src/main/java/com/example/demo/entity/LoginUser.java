package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Users")
public class LoginUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incrementを表す
	 private Integer id; 
	@Column(nullable=false,unique=true,name="user_id")
	private String userId;
	@Column(nullable=false,unique=true,name="user_name")
	private String userName;
	@Column(nullable=false,name="hash_password")
	private String password;
	@Column(nullable=false)
	private String role;
}
