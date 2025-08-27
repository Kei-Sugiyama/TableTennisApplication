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
@Table(name="Matches")
public class Matches {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incrementを表す
	private Integer id;
	@Column(nullable=false,name="type_id")
	private Integer typeId;
	@Column(nullable=false,name="sets_count_id")
	private Integer setsCountId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private Integer usersId;
	@Column(nullable=false)
	private String rivalName;
	@Column
	private String comment;
	@Column
	private LocalDate date;
	
}
