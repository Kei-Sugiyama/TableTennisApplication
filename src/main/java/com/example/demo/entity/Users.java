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
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incrementを表す
	private Integer id;
	@Column(nullable=false,unique=true, name="login_id")
	private String loginId;
	@Column(nullable=false, name="user_name")
	private String userName;
	@Column(nullable = false ,name="hash_password")
    private String hashPassword;
	@Column(nullable = false ,name="role")
	private String role;
}
