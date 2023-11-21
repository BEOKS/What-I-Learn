package com.example.querydsl.entity;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.entity.QStudent.student;

@Repository
public class StudentQueryDSLImpl implements StudentQueryDSL{
    private final JPAQueryFactory jpaQueryFactory;

    public StudentQueryDSLImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<StudentA> findAllByQuery() {
        return jpaQueryFactory.select(student)
                .from(student)
                .fetch();
    }

    @Override
    public List<StudentA.Grade> findAllGradeDistinct() {
        return jpaQueryFactory.select(Expressions.asEnum(student.grade))
                .from(student)
                .distinct()
                .fetch();
    }
}
