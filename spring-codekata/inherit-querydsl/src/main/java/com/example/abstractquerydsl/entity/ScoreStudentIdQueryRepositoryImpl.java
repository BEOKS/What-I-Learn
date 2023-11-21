package com.example.abstractquerydsl.entity;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.example.abstractquerydsl.entity.QScore.score;

@Repository
public class ScoreStudentIdQueryRepositoryImpl extends StudentIdQueryRepositoryImpl<Score> {
    public ScoreStudentIdQueryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    EntityPathBase<Score> getTable() {
        return score;
    }

    @Override
    NumberPath<Long> getStudentId() {
        return score.id.studentId;
    }
}
