package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.demo.dao.Score;
import com.example.demo.dto.Response;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.utility.Utility;


public class ScoreServiceTest {
	
	@InjectMocks
	private ScoreService scoreService;

	@Mock
	private ScoreRepository scoreRepository;
	

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddScore() throws Exception {		
		scoreService.addScore(Utility.getScoreDto("xxx", 34));
        verify(scoreRepository, Mockito.times(1)).save(Mockito.any());  
	}
	
	@Test
	public void testAddStudentsWithException() throws Exception {
		when(scoreRepository.save(any())).thenThrow(RuntimeException.class);
		try {
			scoreService.addScore(Utility.getScoreDto("xxx", 34));
	    } catch (Exception e){
	    	assertTrue(e instanceof Exception);
	    }
	}
	
	
	@Test
	public void testExistsByWord() {
		Score score = new Score();
		score.setScore(34);
		score.setWord("xxx");
		
		when(scoreRepository.findByWord(anyString())).thenReturn(Optional.of(score));

		boolean isDataExist = scoreService.existsByWord(Utility.getScoreDto("xxx", 34));
		assertThat(isDataExist).isTrue();	
	}
	
	
	@Test
	public void testGetTopScores() {
		Score score = new Score();
		score.setScore(34);
		score.setWord("xxx");
		
		when(scoreRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<Score>(Arrays.asList(score)));

		Response response = scoreService.getTopScores();
		assertThat(response).isNotNull();
		assertThat(response.getScores()).isNotNull();
	}

}
