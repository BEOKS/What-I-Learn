package com.example.querydsl.entity;

import java.util.List;

public interface StudentQueryDSL {
    List<StudentA> findAllByQuery();

    List<StudentA.Grade> findAllGradeDistinct();
}
