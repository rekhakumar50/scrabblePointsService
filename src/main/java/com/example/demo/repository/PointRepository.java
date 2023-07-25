package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Point;

public interface PointRepository extends JpaRepository<Point, String> {

}
