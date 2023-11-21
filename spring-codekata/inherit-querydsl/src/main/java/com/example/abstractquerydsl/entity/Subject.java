package com.example.abstractquerydsl.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Subject extends CommonEntity{
    @EmbeddedId
    private SubjectId id;
    private String description;

    // 생성자, getter, setter 메소드 생략
}
