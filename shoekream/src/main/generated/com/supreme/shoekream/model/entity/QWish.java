package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWish is a Querydsl query type for Wish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWish extends EntityPathBase<Wish> {

    private static final long serialVersionUID = -1848773380L;

    public static final QWish wish = new QWish("wish");

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> memberIdx = createNumber("memberIdx", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> productIdx = createNumber("productIdx", Long.class);

    public final StringPath size = createString("size");

    public QWish(String variable) {
        super(Wish.class, forVariable(variable));
    }

    public QWish(Path<? extends Wish> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWish(PathMetadata metadata) {
        super(Wish.class, metadata);
    }

}

