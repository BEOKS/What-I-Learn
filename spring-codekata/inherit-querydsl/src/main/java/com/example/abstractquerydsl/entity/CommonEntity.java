package com.example.abstractquerydsl.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

@MappedSuperclass
public abstract class CommonEntity {
    protected String studentName;
    protected String subjectName;

    // 생성자, getter, setter 메소드 생략
}
