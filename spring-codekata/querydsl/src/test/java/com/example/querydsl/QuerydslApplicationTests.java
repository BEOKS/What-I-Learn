package com.example.querydsl;

import com.example.querydsl.entity.StudentA;
import com.example.querydsl.entity.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class QuerydslApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void storeSample(){
        ArrayList<StudentA> studentAS = new ArrayList<>();
        studentAS.add(new StudentA(1L,"학생1","컴퓨터학과", StudentA.Grade.freshman));
        studentAS.add(new StudentA(2L,"학생2","컴퓨터학과", StudentA.Grade.sophomore));
        studentAS.add(new StudentA(3L,"학생3","컴퓨터학과", StudentA.Grade.junior));
        studentAS.add(new StudentA(4L,"학생4","컴퓨터학과", StudentA.Grade.senior));
        studentRepository.saveAll(studentAS);

    }
    @Test
    void findAll() {
        List<StudentA> all = studentRepository.findAll();
        for (StudentA studentA : all) {
            System.out.println("student = " + studentA);
        }
    }

    @Test
    void findAllByJdbcTemplate() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from student");
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> row : rows) {
            for (Map.Entry<String, Object> column : row.entrySet()) {
                result.append(column.getKey()).append(": ").append(column.getValue()).append("\t");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    @Test
    void findAllByQuery() {
        List<StudentA> all = studentRepository.findAllByQuery();
        for (StudentA studentA : all) {
            System.out.println("student = " + studentA);
        }
    }


    @Test
    void findAllGrade() {
        List<StudentA.Grade> all = studentRepository.findAllGradeDistinct();
        for (StudentA.Grade gradle : all) {
            System.out.println("gradle = " + gradle);
        }
    }

}
