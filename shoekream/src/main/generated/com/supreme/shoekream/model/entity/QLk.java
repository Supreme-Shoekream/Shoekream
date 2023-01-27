package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLk is a Querydsl query type for Lk
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLk extends EntityPathBase<Lk> {

    private static final long serialVersionUID = -1584045292L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLk lk = new QLk("lk");

    public final QBoard board;

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMember member;

    public QLk(String variable) {
        this(Lk.class, forVariable(variable), INITS);
    }

    public QLk(Path<? extends Lk> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLk(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLk(PathMetadata metadata, PathInits inits) {
        this(Lk.class, metadata, inits);
    }

    public QLk(Class<? extends Lk> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

