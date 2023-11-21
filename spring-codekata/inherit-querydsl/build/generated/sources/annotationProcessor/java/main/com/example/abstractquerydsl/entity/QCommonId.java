package com.example.abstractquerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonId is a Querydsl query type for CommonId
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCommonId extends EntityPathBase<CommonId> {

    private static final long serialVersionUID = -396458453L;

    public static final QCommonId commonId = new QCommonId("commonId");

    public final NumberPath<Long> studentId = createNumber("studentId", Long.class);

    public final NumberPath<Long> subjectId = createNumber("subjectId", Long.class);

    public QCommonId(String variable) {
        super(CommonId.class, forVariable(variable));
    }

    public QCommonId(Path<? extends CommonId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonId(PathMetadata metadata) {
        super(CommonId.class, metadata);
    }

}

