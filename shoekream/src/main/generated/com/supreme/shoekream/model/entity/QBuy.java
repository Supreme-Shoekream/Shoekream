package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBuy is a Querydsl query type for Buy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuy extends EntityPathBase<Buy> {

    private static final long serialVersionUID = -1860772975L;

    public static final QBuy buy = new QBuy("buy");

    public final NumberPath<Long> cardIdx = createNumber("cardIdx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> memberIdx = createNumber("memberIdx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Period> period = createEnum("period", com.supreme.shoekream.model.enumclass.Period.class);

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> productIdx = createNumber("productIdx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Progress> progress = createEnum("progress", com.supreme.shoekream.model.enumclass.Progress.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.SellBuyStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.SellBuyStatus.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Type> type = createEnum("type", com.supreme.shoekream.model.enumclass.Type.class);

    public QBuy(String variable) {
        super(Buy.class, forVariable(variable));
    }

    public QBuy(Path<? extends Buy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuy(PathMetadata metadata) {
        super(Buy.class, metadata);
    }

}

