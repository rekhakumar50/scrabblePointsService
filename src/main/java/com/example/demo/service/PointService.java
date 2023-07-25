package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Point;
import com.example.demo.dto.Response;
import com.example.demo.repository.PointRepository;

@Service
public class PointService {
	private static final Logger LOG = LoggerFactory.getLogger(ScoreService.class);

	@Autowired
	private PointRepository pointRepository;
	
	
	public Response getAllPoints() {
		Response response = new Response();
		try {
			List<Point> points = pointRepository.findAll();
		
			Map<String, Integer> pointsMap = points.stream()
							.filter(Objects::nonNull)
							.collect(Collectors.toMap(Point::getLetter, Point::getPoint, (oldV, newV) -> newV, HashMap::new));
			response.setPoints(pointsMap);
		} catch(Exception e) {
			LOG.error("Error while retrieving points from DB");
		}
		return response;
	}
	
}
