package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPenalty is a Querydsl query type for Penalty
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPenalty extends EntityPathBase<Penalty> {

    private static final long serialVersionUID = -183953388L;

    public static final QPenalty penalty = new QPenalty("penalty");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> memberIdx = createNumber("memberIdx", Long.class);

    public final NumberPath<Long> productIdx = createNumber("productIdx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Reason> reason = createEnum("reason", com.supreme.shoekream.model.enumclass.Reason.class);

    public QPenalty(String variable) {
        super(Penalty.class, forVariable(variable));
    }

    public QPenalty(Path<? extends Penalty> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPenalty(PathMetadata metadata) {
        super(Penalty.class, metadata);
    }

}

