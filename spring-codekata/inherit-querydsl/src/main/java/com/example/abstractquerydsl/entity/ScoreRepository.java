package com.example.abstractquerydsl.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScoreId>,
        ScoreQueryDSLRepository {
}