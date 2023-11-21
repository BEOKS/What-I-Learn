package com.example.abstractquerydsl.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;


@Entity
public class Student extends CommonEntity{
    @EmbeddedId
    private StudentSubjectId id;
    private String grade;

    // 생성자, getter, setter 메소드 생략
}

