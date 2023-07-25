package com.example.demo.validation;

import static com.example.demo.util.Utility.*;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ScoreDto;

@Component
public class RequestValidation {
	private static final Logger LOG = LoggerFactory.getLogger(RequestValidation.class);

	
	/**
	 * Validate Student object with valid email and name
	 * @param student
	 * @return
	 */
	public boolean validateStudent(final ScoreDto scoreDto) {
		boolean isValid = false;
		if(Objects.nonNull(scoreDto) && 
				scoreDto.getScore() != null && 
				StringUtils.isNotEmpty(scoreDto.getWord())) {
			isValid = validateWord(scoreDto.getWord());
		} 
		LOG.debug("validateStudent() isValid: {}", isValid);
		return isValid;
	}
	
}
