package com.example.querydsl;

import com.example.querydsl.entity.Student;
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
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1L,"학생1","컴퓨터학과", Student.Grade.freshman));
        students.add(new Student(2L,"학생2","컴퓨터학과", Student.Grade.sophomore));
        students.add(new Student(3L,"학생3","컴퓨터학과", Student.Grade.junior));
        students.add(new Student(4L,"학생4","컴퓨터학과", Student.Grade.senior));
        studentRepository.saveAll(students);

    }
    @Test
    void findAll() {
        List<Student> all = studentRepository.findAll();
        for (Student student : all) {
            System.out.println("student = " + student);
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
        List<Student> all = studentRepository.findAllByQuery();
        for (Student student : all) {
            System.out.println("student = " + student);
        }
    }


    @Test
    void findAllGrade() {
        List<Student.Grade> all = studentRepository.findAllGradeDistinct();
        for (Student.Grade gradle : all) {
            System.out.println("gradle = " + gradle);
        }
    }

}
