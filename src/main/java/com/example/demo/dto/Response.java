package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response implements Serializable {
	private static final long serialVersionUID = 809723884445633680L;
	
	private ResponseInformation information;	
	private List<ScoreDto> scores;
	private Map<String, Integer> points;
	
}
