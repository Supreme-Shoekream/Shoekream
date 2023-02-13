package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventProduct is a Querydsl query type for EventProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventProduct extends EntityPathBase<EventProduct> {

    private static final long serialVersionUID = 662425674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventProduct eventProduct = new QEventProduct("eventProduct");

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final ListPath<EventMember, QEventMember> eventMember = this.<EventMember, QEventMember>createList("eventMember", EventMember.class, QEventMember.class, PathInits.DIRECT2);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath img = createString("img");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QProduct product;

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QEventProduct(String variable) {
        this(EventProduct.class, forVariable(variable), INITS);
    }

    public QEventProduct(Path<? extends EventProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventProduct(PathMetadata metadata, PathInits inits) {
        this(EventProduct.class, metadata, inits);
    }

    public QEventProduct(Class<? extends EventProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

