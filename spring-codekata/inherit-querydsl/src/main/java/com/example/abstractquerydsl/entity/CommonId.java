package com.example.abstractquerydsl.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@MappedSuperclass
public class CommonId implements Serializable {
    private Long studentId;
    private Long subjectId;

    // 생성자, getter, setter, hashCode, equals 메소드 생략
}
