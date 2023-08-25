package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Score;
import com.example.demo.dto.Response;
import com.example.demo.dto.ScoreDto;
import com.example.demo.mapper.ScoreMapper;
import com.example.demo.repository.ScoreRepository;

@Service
public class ScoreService {
	private static final Logger LOG = LoggerFactory.getLogger(ScoreService.class);

	@Autowired
	private ScoreRepository scoreRepository;
	
	
	/**
	 * Add Score to DB if is not present in DB
	 * @param scoreDto
	 * @throws Exception 
	 */
	public void addScore(ScoreDto scoreDto) {
		try {
			scoreRepository.save(ScoreMapper.convertToScore(scoreDto));
		} catch(Exception e) {
			LOG.error("Error while adding score to DB: {}", e.getMessage());
		}
	}
	
	
	/**
	 * Get top 10 scores
	 * @return
	 */
	public Response getTopScores() {
		Response response = new Response();
		response.setScores(this.getAllLimited());
        return response;
    }
	
	
	/**
	 * Check word exist in DB
	 * @param scoreDto
	 * @return
	 */
	public boolean existsByWord(final ScoreDto scoreDto) {
		boolean isWordExist = false;
		if(Objects.nonNull(scoreDto) 
				&& StringUtils.isNotBlank(scoreDto.getWord())) {
			Optional<Score> scoreOp = scoreRepository.findByWord(scoreDto.getWord());
			isWordExist = scoreOp.isPresent();	
		}
		return isWordExist;
	}
	
	
	/**
	 * Get Top 10 Scores from DB
	 * @return
	 */
	private List<ScoreDto> getAllLimited() {
		List<ScoreDto> scores = null;
        Page<Score> page = scoreRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Order.desc("score"), Sort.Order.asc("word"))));
        if(Objects.nonNull(page) && CollectionUtils.isNotEmpty(page.getContent())) {
        	scores = page.getContent().stream()
        							.filter(Objects::nonNull)
        							.map(ScoreMapper::convertToScoreDto)
        							.collect(Collectors.toList());
        }
        return scores;
    }

}
