package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseInformation;

@RestControllerAdvice
public class ExceptionAdvice {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST) 
	public ResponseEntity<Response>  handleDuplicateDataException(DuplicateDataException e) { 
		LOG.error("The given data already exist in DB");
		Response response = new Response();
		response.setInformation(new ResponseInformation(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleInternalSeverException(Exception e) {
		LOG.error("Exception occured while processing data");
		Response response = new Response();
		response.setInformation(new ResponseInformation(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler()
	@ResponseStatus(value = HttpStatus.BAD_REQUEST) 
	public ResponseEntity<Response>  handleInvalidDataException(InvalidDataException e) { 
		LOG.error("Data passed is not valid");
		Response response = new Response();
		response.setInformation(new ResponseInformation(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	}
	
}
