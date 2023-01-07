package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlacklist is a Querydsl query type for Blacklist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlacklist extends EntityPathBase<Blacklist> {

    private static final long serialVersionUID = -432778776L;

    public static final QBlacklist blacklist = new QBlacklist("blacklist");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> memberIdx = createNumber("memberIdx", Long.class);

    public final StringPath period = createString("period");

    public final StringPath reason = createString("reason");

    public QBlacklist(String variable) {
        super(Blacklist.class, forVariable(variable));
    }

    public QBlacklist(Path<? extends Blacklist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlacklist(PathMetadata metadata) {
        super(Blacklist.class, metadata);
    }

}

