package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="records")
public class MatchRecords {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incrementを表す
	 private Integer id; 
	@Column(name="match_name", nullable=false)
	private String matchName;
	@Column(name="user_name", nullable=false)
	private String userName;
	@Column(nullable=false)
	private Integer mySet;
	@Column(nullable=false,name="1setMyScore")
	private Integer firstSetMyScore;
	@Column(nullable=false,name="1setRivalScore")
	private Integer firstSetRivalScore;
	@Column(nullable=false,name="2setMyScore")
	private Integer secondSetMyScore;
	@Column(nullable=false,name="2setRivalScore")
	private Integer secondSetRivalScore;
	@Column(nullable=false,name="3setMyScore")
	private Integer thirdSetMyScore;
	@Column(nullable=false,name="3setRivalScore")
	private Integer thirdSetRivalScore;
	@Column(nullable=false,name="4setMyScore")
	private Integer fourthSetMyScore;
	@Column(nullable=false,name="4setRivalScore")
	private Integer fourthSetRivalScore;
	@Column(nullable=false,name="5setMyScore")
	private Integer fifthSetMyScore;
	@Column(nullable=false,name="5setRivalScore")
	private Integer fifthSetRivalScore;
	@Column(nullable=false)
	private Integer rivalSet;
	@Column(name="rival_name", nullable=false)
	private String rivalName;
	@Column(nullable=false)
	private LocalDate date;
}
