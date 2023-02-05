package com.supreme.admin.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 1314920381L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotice notice = new QNotice("notice");

    public final com.supreme.admin.model.config.QBaseEntity _super = new com.supreme.admin.model.config.QBaseEntity(this);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QMember createdBy;

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final QMember modifiedBy;

    public final StringPath title = createString("title");

    public QNotice(String variable) {
        this(Notice.class, forVariable(variable), INITS);
    }

    public QNotice(Path<? extends Notice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotice(PathMetadata metadata, PathInits inits) {
        this(Notice.class, metadata, inits);
    }

    public QNotice(Class<? extends Notice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QMember(forProperty("createdBy")) : null;
        this.modifiedBy = inits.isInitialized("modifiedBy") ? new QMember(forProperty("modifiedBy")) : null;
    }

}

