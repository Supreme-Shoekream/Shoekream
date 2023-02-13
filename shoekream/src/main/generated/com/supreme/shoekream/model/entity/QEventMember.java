package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventMember is a Querydsl query type for EventMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventMember extends EntityPathBase<EventMember> {

    private static final long serialVersionUID = -492228641L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventMember eventMember = new QEventMember("eventMember");

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QEventProduct eventProduct;

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QEventMember(String variable) {
        this(EventMember.class, forVariable(variable), INITS);
    }

    public QEventMember(Path<? extends EventMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventMember(PathMetadata metadata, PathInits inits) {
        this(EventMember.class, metadata, inits);
    }

    public QEventMember(Class<? extends EventMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eventProduct = inits.isInitialized("eventProduct") ? new QEventProduct(forProperty("eventProduct"), inits.get("eventProduct")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

