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

    public final NumberPath<Long> accountIdx = createNumber("accountIdx", Long.class);

    public final NumberPath<Long> addressIdx = createNumber("addressIdx", Long.class);

    public final QBuy buy;

    public final NumberPath<Long> card_idx = createNumber("card_idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMember member;

    public final EnumPath<com.supreme.shoekream.model.enumclass.Period> period = createEnum("period", com.supreme.shoekream.model.enumclass.Period.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> productIdx = createNumber("productIdx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Progress> progress = createEnum("progress", com.supreme.shoekream.model.enumclass.Progress.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.SellBuyStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.SellBuyStatus.class);

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
    }

}

