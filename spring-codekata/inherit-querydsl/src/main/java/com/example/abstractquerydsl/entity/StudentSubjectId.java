package com.example.abstractquerydsl.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(callSuper = false)
public class StudentSubjectId extends CommonId {
    private Long studentSubjectId;

    // 생성자, getter, setter, hashCode, equals 메소드 생략
}
