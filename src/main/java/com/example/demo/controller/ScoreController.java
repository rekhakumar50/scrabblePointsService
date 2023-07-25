package com.example.demo.controller;

import static com.example.demo.constant.Constants.DATA_EXIST_ERROR;
import static com.example.demo.constant.Constants.DATA_RETRIEVED;
import static com.example.demo.constant.Constants.DATA_SAVED;
import static com.example.demo.constant.Constants.INTERNAL_SERVER_RETRIEVING_ERROR;
import static com.example.demo.constant.Constants.INVALID_DATA_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseInformation;
import com.example.demo.dto.ScoreDto;
import com.example.demo.exception.DuplicateDataException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.service.ScoreService;
import com.example.demo.validation.RequestValidation;

@RestController
@RequestMapping("/scores")
public class ScoreController {
	private static final Logger LOG = LoggerFactory.getLogger(ScoreController.class);

	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private RequestValidation requestValidation;
	
	
	/**
	 * Add Score to DB if is not present in DB
	 * @param scoreDto
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Response> addScore(@RequestBody ScoreDto scoreDto) {
		this.processValidation(requestValidation.validateStudent(scoreDto));
		Response response = new Response();
		boolean isDataExist = scoreService.existsByWord(scoreDto);
		if(!isDataExist) {
			scoreService.addScore(scoreDto);
		} else {
			LOG.error("Data already Exist in DB");
			throw new DuplicateDataException(DATA_EXIST_ERROR);
		}
		LOG.info("Score added in DB");
		response.setInformation(new ResponseInformation(HttpStatus.CREATED.value(), DATA_SAVED));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	/**
	 * Get top 10 scores
	 * @return
	 * @throws Exception 
	 */
	@GetMapping
	public ResponseEntity<Response> getTopScores() throws Exception {
		Response response = new Response();
		try {
			response = scoreService.getTopScores();
		} catch(Exception e) {
			LOG.error("Error while Retrieving Data");
			throw new Exception(INTERNAL_SERVER_RETRIEVING_ERROR);
		}
		LOG.info("Retrieved Top 10 scores");
		response.setInformation(new ResponseInformation(HttpStatus.OK.value(), DATA_RETRIEVED));
		return new ResponseEntity<>(response, HttpStatus.OK);    
	}
	
	
	/**
	 * throws Exception when the input validation fails
	 * @param isValid
	 */
	private void processValidation(boolean isValid) {
		if(!isValid) {
			LOG.error("Request Data validation is failed!!");
			throw new InvalidDataException(INVALID_DATA_ERROR);
		}
	}
	

}
