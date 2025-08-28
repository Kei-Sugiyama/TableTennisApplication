package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@ManyToOne //Usersエンティティとの結合：JPQL
	@JoinColumn(name="user_id",referencedColumnName = "user_id")//DBにおける外部キー列名 Usersエンティティの主キーと結合
	private Users users;
	@Column(nullable=false)
	private String rivalName;
	@Column
	private String comment;
	@Column
	private LocalDate date;
	
}
