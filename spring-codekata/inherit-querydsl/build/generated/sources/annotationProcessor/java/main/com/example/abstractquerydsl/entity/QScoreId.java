package com.example.abstractquerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScoreId is a Querydsl query type for ScoreId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QScoreId extends BeanPath<ScoreId> {

    private static final long serialVersionUID = -563217176L;

    public static final QScoreId scoreId1 = new QScoreId("scoreId1");

    public final QCommonId _super = new QCommonId(this);

    public final NumberPath<Long> scoreId = createNumber("scoreId", Long.class);

    //inherited
    public final NumberPath<Long> studentId = _super.studentId;

    //inherited
    public final NumberPath<Long> subjectId = _super.subjectId;

    public QScoreId(String variable) {
        super(ScoreId.class, forVariable(variable));
    }

    public QScoreId(Path<? extends ScoreId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScoreId(PathMetadata metadata) {
        super(ScoreId.class, metadata);
    }

}

