package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.dao.Point;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PointRepositoryTest {
	
	@Autowired
	private PointRepository pointRepository;

	
	@Test
	public void testFindAllPoints() {
		List<Point> pointList = pointRepository.findAll();
		assertThat(pointList).isNotNull();
		assertEquals(26, pointList.size());
	}
	
}
