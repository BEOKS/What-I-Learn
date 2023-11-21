package com.example.abstractquerydsl.entity;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreQueryDSLRepositoryImpl extends StudentIdQueryRepositoryImpl<Score> implements ScoreQueryDSLRepository{
    public ScoreQueryDSLRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    EntityPathBase<Score> getTable() {
        return QScore.score;
    }

    @Override
    NumberPath<Long> getStudentId() {
        return QScore.score.id.studentId;
    }
}
