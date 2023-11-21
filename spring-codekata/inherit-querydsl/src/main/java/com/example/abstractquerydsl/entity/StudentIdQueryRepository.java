package com.example.abstractquerydsl.entity;

import java.util.List;

public interface StudentIdQueryRepository<T> {
    List<T> findAllByStudentId(Long studentId);
}
