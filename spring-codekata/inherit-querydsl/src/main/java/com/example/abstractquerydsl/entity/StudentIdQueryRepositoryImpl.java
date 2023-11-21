package com.example.abstractquerydsl.entity;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class StudentIdQueryRepositoryImpl<T extends CommonEntity> implements StudentIdQueryRepository<T> {

    private final JPAQueryFactory queryFactory;
    public StudentIdQueryRepositoryImpl(EntityManager entityManager){
        this.queryFactory=new JPAQueryFactory(entityManager);
    }

    @Override
    public List<T> findAllByStudentId(Long studentId) {
        return queryFactory.select(getTable())
                .from(getTable())
                .where(getStudentId().eq(studentId))
                .fetch();
    }

    abstract EntityPathBase<T> getTable();

    abstract NumberPath<Long> getStudentId();
}
