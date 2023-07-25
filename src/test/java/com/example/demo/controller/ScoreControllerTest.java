package com.example.demo.controller;

import static com.example.demo.constant.Constants.INTERNAL_SERVER_RETRIEVING_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Response;
import com.example.demo.exception.DuplicateDataException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.service.ScoreService;
import com.example.demo.utility.Utility;
import com.example.demo.validation.RequestValidation;

public class ScoreControllerTest {

	@InjectMocks
	private ScoreController scoreController;
	
	@Mock
	private ScoreService scoreService;
	
	@Mock
	private RequestValidation requestValidation;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void testGetTopScores() throws Exception {
		when(scoreService.getTopScores()).thenReturn(new Response());
		ResponseEntity<Response> responseEntity = scoreController.getTopScores();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	
	@Test
	public void testGetTopScoresWithException() throws Exception {
	    when(scoreService.getTopScores()).thenThrow(new RuntimeException(INTERNAL_SERVER_RETRIEVING_ERROR));
	    try {
	    	scoreController.getTopScores();
	    } catch (Exception e){
	    	assertTrue(e instanceof Exception);
	    }
	}
	
	
	@Test
	public void testAddScore() {
		when(scoreService.existsByWord(any())).thenReturn(false);
		when(requestValidation.validateStudent(any())).thenReturn(true);
		ResponseEntity<Response> responseEntity = scoreController.addScore(Utility.getScoreDto("xxxx", 4));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}
	
	
	@Test
	public void testAddScoreWithDataExistException() {
		when(scoreService.existsByWord(any())).thenReturn(true);
		when(requestValidation.validateStudent(any())).thenReturn(true);
		try {
	    	scoreController.addScore(Utility.getScoreDto("xxxx", 4));
	    } catch (Exception e){
	    	assertTrue(e instanceof DuplicateDataException);
	    }
	}
	
	
	@Test
	public void testAddScoreWithInvalidDataException() {
		when(scoreService.existsByWord(any())).thenReturn(false);
		when(requestValidation.validateStudent(any())).thenReturn(false);
		try {
	    	scoreController.addScore(Utility.getScoreDto("xxx3x", 4));
	    } catch (Exception e){
	    	assertTrue(e instanceof InvalidDataException);
	    }
	}
	
}
