package com.example.abstractquerydsl.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.ToString;

@Entity
public class Score extends CommonEntity{
    @EmbeddedId
    private ScoreId id;
    private Integer point;

    // 생성자, getter, setter 메소드 생략
}
