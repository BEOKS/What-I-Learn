package com.example.querydsl.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    Long id;
    String name;
    String department;
    //@Convert(converter = Grade.GradeConverter.class)
    Grade grade;

    public Student(Long id, String name, String department, Grade grade) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.grade = grade;
    }
    public Student(){}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", grade=" + grade +
                '}';
    }

    public enum Grade{
         freshman, sophomore, junior, senior;
         @Converter(autoApply = true)
        static class GradeConverter implements AttributeConverter<Grade,String> {

            @Override
            public String convertToDatabaseColumn(Grade attribute) {
                return attribute.name();
            }

            @Override
            public Grade convertToEntityAttribute(String dbData) {
                return Grade.valueOf(dbData);
            }
        }
    }
}
