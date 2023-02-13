package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConclusion is a Querydsl query type for Conclusion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConclusion extends EntityPathBase<Conclusion> {

    private static final long serialVersionUID = -636222796L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConclusion conclusion = new QConclusion("conclusion");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath price = createString("price");

    public final QProduct product;

    public QConclusion(String variable) {
        this(Conclusion.class, forVariable(variable), INITS);
    }

    public QConclusion(Path<? extends Conclusion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConclusion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConclusion(PathMetadata metadata, PathInits inits) {
        this(Conclusion.class, metadata, inits);
    }

    public QConclusion(Class<? extends Conclusion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

