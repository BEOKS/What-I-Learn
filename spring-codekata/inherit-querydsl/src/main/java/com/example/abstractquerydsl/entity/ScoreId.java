package com.example.abstractquerydsl.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(callSuper = false)
public class ScoreId extends CommonId {
    private Long scoreId;

    // 생성자, getter, setter, hashCode, equals 메소드 생략
}
