package com.example.abstractquerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentSubjectId is a Querydsl query type for StudentSubjectId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudentSubjectId extends BeanPath<StudentSubjectId> {

    private static final long serialVersionUID = 1110917169L;

    public static final QStudentSubjectId studentSubjectId1 = new QStudentSubjectId("studentSubjectId1");

    public final QCommonId _super = new QCommonId(this);

    //inherited
    public final NumberPath<Long> studentId = _super.studentId;

    public final NumberPath<Long> studentSubjectId = createNumber("studentSubjectId", Long.class);

    //inherited
    public final NumberPath<Long> subjectId = _super.subjectId;

    public QStudentSubjectId(String variable) {
        super(StudentSubjectId.class, forVariable(variable));
    }

    public QStudentSubjectId(Path<? extends StudentSubjectId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentSubjectId(PathMetadata metadata) {
        super(StudentSubjectId.class, metadata);
    }

}

