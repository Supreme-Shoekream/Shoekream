package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuy is a Querydsl query type for Buy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuy extends EntityPathBase<Buy> {

    private static final long serialVersionUID = -1860772975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBuy buy = new QBuy("buy");

    public final StringPath cardInfo = createString("cardInfo");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath deliveryMemo = createString("deliveryMemo");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMember member;

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final QProduct product;

    public final EnumPath<com.supreme.shoekream.model.enumclass.Progress> progress = createEnum("progress", com.supreme.shoekream.model.enumclass.Progress.class);

    public final StringPath receiver = createString("receiver");

    public final StringPath receiverAddress = createString("receiverAddress");

    public final StringPath receiverHp = createString("receiverHp");

    public final QSell sell;

    public final EnumPath<com.supreme.shoekream.model.enumclass.OrderStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.OrderStatus.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Type> type = createEnum("type", com.supreme.shoekream.model.enumclass.Type.class);

    public final NumberPath<Integer> usePoint = createNumber("usePoint", Integer.class);

    public QBuy(String variable) {
        this(Buy.class, forVariable(variable), INITS);
    }

    public QBuy(Path<? extends Buy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBuy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBuy(PathMetadata metadata, PathInits inits) {
        this(Buy.class, metadata, inits);
    }

    public QBuy(Class<? extends Buy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.sell = inits.isInitialized("sell") ? new QSell(forProperty("sell"), inits.get("sell")) : null;
    }

}

