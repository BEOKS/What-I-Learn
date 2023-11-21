package com.example.abstractquerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubjectId is a Querydsl query type for SubjectId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSubjectId extends BeanPath<SubjectId> {

    private static final long serialVersionUID = -1955866046L;

    public static final QSubjectId subjectId1 = new QSubjectId("subjectId1");

    public final QCommonId _super = new QCommonId(this);

    //inherited
    public final NumberPath<Long> studentId = _super.studentId;

    //inherited
    public final NumberPath<Long> subjectId = _super.subjectId;

    public final NumberPath<Long> uniqueSubjectKey = createNumber("uniqueSubjectKey", Long.class);

    public QSubjectId(String variable) {
        super(SubjectId.class, forVariable(variable));
    }

    public QSubjectId(Path<? extends SubjectId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubjectId(PathMetadata metadata) {
        super(SubjectId.class, metadata);
    }

}

