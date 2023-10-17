package com.example.querydsl.entity;

import java.util.List;

public interface StudentQueryDSL {
    List<Student> findAllByQuery();

    List<Student.Grade> findAllGradeDistinct();
}
