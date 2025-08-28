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
@Table(name="Sets")
public class Sets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incrementを表す
	private Integer id;
	@Column(name="matches_id")
	private Integer matchesId;
	@Column(name="user_score")
	private Integer userScore;
	@Column(name="rival_score")
	private Integer rivalScore;
	@Column
	private String comment;
}
