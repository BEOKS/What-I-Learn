package com.example.testcontainermysql.api;

import com.example.testcontainermysql.domain.Student;
import com.example.testcontainermysql.domain.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/all")
    List<Student> findAll(){
        return studentRepository.findAll();
    }
}
