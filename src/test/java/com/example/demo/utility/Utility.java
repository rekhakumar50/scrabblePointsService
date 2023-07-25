package com.example.demo.utility;

import com.example.demo.dto.ScoreDto;

public class Utility {

	public static ScoreDto getScoreDto(String word, int score) {
		ScoreDto scoreDto = new ScoreDto();
		scoreDto.setWord(word);
		scoreDto.setScore(score);
		return scoreDto;
	}
	
}
