package com.example.demo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "points")
public class Point {

	@Id
	private String letter;
	
	@Column(nullable = false)
	private Integer point;
	
}
