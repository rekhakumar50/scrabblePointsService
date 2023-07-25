package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dao.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	
	Optional<Score> findByWord(String word);
	
    Page<Score> findAll(Pageable pageable);  
    
    @Query(value = "SELECT * FROM Scores s ORDER BY s.score DESC LIMIT 10", nativeQuery = true)
    List<Score> getScoreWithLimit();

}
