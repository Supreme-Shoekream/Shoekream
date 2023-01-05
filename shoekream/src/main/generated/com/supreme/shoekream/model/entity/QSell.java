package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSell is a Querydsl query type for Sell
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSell extends EntityPathBase<Sell> {

    private static final long serialVersionUID = -1848896601L;

    public static final QSell sell = new QSell("sell");

    public final NumberPath<Long> account_idx = createNumber("account_idx", Long.class);

    public final NumberPath<Long> address_idx = createNumber("address_idx", Long.class);

    public final NumberPath<Long> card_idx = createNumber("card_idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> member_idx = createNumber("member_idx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Period> period = createEnum("period", com.supreme.shoekream.model.enumclass.Period.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> product_idx = createNumber("product_idx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Progress> progress = createEnum("progress", com.supreme.shoekream.model.enumclass.Progress.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.SellBuyStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.SellBuyStatus.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Type> type = createEnum("type", com.supreme.shoekream.model.enumclass.Type.class);

    public QSell(String variable) {
        super(Sell.class, forVariable(variable));
    }

    public QSell(Path<? extends Sell> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSell(PathMetadata metadata) {
        super(Sell.class, metadata);
    }

}

