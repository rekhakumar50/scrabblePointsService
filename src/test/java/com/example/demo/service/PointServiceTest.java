package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.Point;
import com.example.demo.dto.Response;
import com.example.demo.repository.PointRepository;

public class PointServiceTest {
	
	@InjectMocks
	private PointService pointService;
	
	@Mock
	private PointRepository pointRepository;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testGetAllPoints() {
		Point point1 = new Point();
		point1.setLetter("a");
		point1.setPoint(2);
		
		Point point2 = new Point();
		point2.setLetter("b");
		point2.setPoint(8);
		
		when(pointRepository.findAll()).thenReturn(Arrays.asList(point1, point2));

		Response response = pointService.getAllPoints();
		assertThat(response).isNotNull();
		assertThat(response.getPoints()).isNotNull();	
	}

}
