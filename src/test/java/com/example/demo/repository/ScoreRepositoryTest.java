package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.dao.Score;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ScoreRepositoryTest {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	
	@Test
	public void testSaveScore() {
		Score score = new Score();
		score.setScore(34);
		score.setWord("xxx");
		
		Score scoreRes = scoreRepository.save(score);
		assertThat(scoreRes).isNotNull();		
	}
	
	
	@Test
	public void testFindByWord() {
		Score score = new Score();
		score.setScore(34);
		score.setWord("xxx");
		
		scoreRepository.save(score);
		Optional<Score> scoreOp = scoreRepository.findByWord("xxx");
		assertThat(scoreOp.isPresent()).isTrue();		
	}
	
	
	@Test
	public void testGetScoreWithLimit() {
		List<Score> scoreList = scoreRepository.getScoreWithLimit();
		assertThat(scoreList).isNotNull();	
	}

}
