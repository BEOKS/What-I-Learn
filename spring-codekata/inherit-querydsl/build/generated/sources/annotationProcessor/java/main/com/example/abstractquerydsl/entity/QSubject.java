package com.example.abstractquerydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubject is a Querydsl query type for Subject
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubject extends EntityPathBase<Subject> {

    private static final long serialVersionUID = -60135737L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubject subject = new QSubject("subject");

    public final QCommonEntity _super = new QCommonEntity(this);

    public final StringPath description = createString("description");

    public final QSubjectId id;

    //inherited
    public final StringPath studentName = _super.studentName;

    //inherited
    public final StringPath subjectName = _super.subjectName;

    public QSubject(String variable) {
        this(Subject.class, forVariable(variable), INITS);
    }

    public QSubject(Path<? extends Subject> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubject(PathMetadata metadata, PathInits inits) {
        this(Subject.class, metadata, inits);
    }

    public QSubject(Class<? extends Subject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QSubjectId(forProperty("id")) : null;
    }

}

