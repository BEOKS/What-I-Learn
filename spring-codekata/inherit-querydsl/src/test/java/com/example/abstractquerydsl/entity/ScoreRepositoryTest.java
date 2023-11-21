package com.example.abstractquerydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ScoreRepositoryTest {

    @Autowired
    ScoreRepository scoreRepository;
    @Test
    void printAll(){
        List<Score> all = scoreRepository.findAll();
        for (Score score : all) {
            System.out.println("score = " + score);
        }
    }

    @Test
    void printByStudent(){
        List<Score> all = scoreRepository.findAllByStudentId(3L);
        for (Score score : all) {
            System.out.println("score = " + score);
        }
    }
}