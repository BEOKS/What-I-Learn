package com.example.abstractquerydsl.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(callSuper = false)
public class SubjectId extends CommonId {
    private Long uniqueSubjectKey;

    // 생성자, getter, setter, hashCode, equals 메소드 생략
}

