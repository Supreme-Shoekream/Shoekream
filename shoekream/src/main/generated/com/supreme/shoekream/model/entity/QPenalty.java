package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPenalty is a Querydsl query type for Penalty
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPenalty extends EntityPathBase<Penalty> {

    private static final long serialVersionUID = -183953388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPenalty penalty = new QPenalty("penalty");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Reason> reason = createEnum("reason", com.supreme.shoekream.model.enumclass.Reason.class);

    public final QSell sell;

    public QPenalty(String variable) {
        this(Penalty.class, forVariable(variable), INITS);
    }

    public QPenalty(Path<? extends Penalty> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPenalty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPenalty(PathMetadata metadata, PathInits inits) {
        this(Penalty.class, metadata, inits);
    }

    public QPenalty(Class<? extends Penalty> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sell = inits.isInitialized("sell") ? new QSell(forProperty("sell"), inits.get("sell")) : null;
    }

}

