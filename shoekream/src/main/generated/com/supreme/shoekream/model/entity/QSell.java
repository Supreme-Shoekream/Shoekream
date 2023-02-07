package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSell is a Querydsl query type for Sell
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSell extends EntityPathBase<Sell> {

    private static final long serialVersionUID = -1848896601L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSell sell = new QSell("sell");

    public final StringPath accountInfo = createString("accountInfo");

    public final QBuy buy;

    public final StringPath cardInfo = createString("cardInfo");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath deliveryMemo = createString("deliveryMemo");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMember member;

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final QProduct product;

    public final EnumPath<com.supreme.shoekream.model.enumclass.SellProgress> progress = createEnum("progress", com.supreme.shoekream.model.enumclass.SellProgress.class);

    public final StringPath sender = createString("sender");

    public final StringPath senderAddress = createString("senderAddress");

    public final StringPath senderHp = createString("senderHp");

    public final EnumPath<com.supreme.shoekream.model.enumclass.OrderStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.OrderStatus.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Type> type = createEnum("type", com.supreme.shoekream.model.enumclass.Type.class);

    public QSell(String variable) {
        this(Sell.class, forVariable(variable), INITS);
    }

    public QSell(Path<? extends Sell> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSell(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSell(PathMetadata metadata, PathInits inits) {
        this(Sell.class, metadata, inits);
    }

    public QSell(Class<? extends Sell> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buy = inits.isInitialized("buy") ? new QBuy(forProperty("buy"), inits.get("buy")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

