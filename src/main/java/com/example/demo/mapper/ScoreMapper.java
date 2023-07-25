package com.example.demo.mapper;

import java.util.Objects;

import com.example.demo.dao.Score;
import com.example.demo.dto.ScoreDto;

public class ScoreMapper {
	
	public static Score convertToScore(final ScoreDto scoreDto) {
		Score score = null;
		if(Objects.nonNull(scoreDto)) {
			score = new Score();
			score.setScore(scoreDto.getScore());
			score.setWord(scoreDto.getWord().toUpperCase());
		}
		
		return score;
	}
	
	
	public static ScoreDto convertToScoreDto(final Score score) {
		ScoreDto scoreDto = null;
		if(Objects.nonNull(score)) {
			scoreDto = new ScoreDto();
			scoreDto.setScore(score.getScore());
			scoreDto.setWord(score.getWord().toUpperCase());
		}
		
		return scoreDto;
	}

}
