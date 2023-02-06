package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlacklist is a Querydsl query type for Blacklist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlacklist extends EntityPathBase<Blacklist> {

    private static final long serialVersionUID = -432778776L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlacklist blacklist = new QBlacklist("blacklist");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMember member;

    public final StringPath reason = createString("reason");

    public QBlacklist(String variable) {
        this(Blacklist.class, forVariable(variable), INITS);
    }

    public QBlacklist(Path<? extends Blacklist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlacklist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlacklist(PathMetadata metadata, PathInits inits) {
        this(Blacklist.class, metadata, inits);
    }

    public QBlacklist(Class<? extends Blacklist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

