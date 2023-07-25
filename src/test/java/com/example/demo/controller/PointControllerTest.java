package com.example.demo.controller;

import static com.example.demo.constant.Constants.INTERNAL_SERVER_RETRIEVING_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Response;
import com.example.demo.service.PointService;

public class PointControllerTest {

	@InjectMocks
	private PointsController pointsController;
	
	@Mock
	private PointService pointService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void testGetAllTeachers() throws Exception {
		when(pointService.getAllPoints()).thenReturn(new Response());
		ResponseEntity<Response> responseEntity = pointsController.getPoints();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	
	@Test
	public void testGetAllTeachersWithException() throws Exception {
	    when(pointService.getAllPoints()).thenThrow(new RuntimeException(INTERNAL_SERVER_RETRIEVING_ERROR));
	    try {
	    	pointsController.getPoints();
	    } catch (Exception e){
	    	assertTrue(e instanceof Exception);
	    }
	}
	
}
