package com.example.demo.controller;

import static com.example.demo.constant.Constants.DATA_RETRIEVED;
import static com.example.demo.constant.Constants.INTERNAL_SERVER_RETRIEVING_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseInformation;
import com.example.demo.service.PointService;

@RestController
@RequestMapping("/points")
public class PointsController {
	private static final Logger LOG = LoggerFactory.getLogger(ScoreController.class);
	
	@Autowired
	private PointService pointService;
	
	
	@GetMapping
	public ResponseEntity<Response> getPoints() throws Exception {		
		Response response = new Response();
		try {
			response = pointService.getAllPoints();
		} catch(Exception e) {
			LOG.error("Error while Retrieving Data");
			throw new Exception(INTERNAL_SERVER_RETRIEVING_ERROR);
		}
		LOG.info("Retrieved Points");
		response.setInformation(new ResponseInformation(HttpStatus.OK.value(), DATA_RETRIEVED));
		return new ResponseEntity<>(response, HttpStatus.OK);    
	}

}
