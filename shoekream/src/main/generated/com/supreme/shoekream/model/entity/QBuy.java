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

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    public final NumberPath<Long> card_idx = createNumber("card_idx", Long.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> member_idx = createNumber("member_idx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Period> period = createEnum("period", com.supreme.shoekream.model.enumclass.Period.class);

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> product_idx = createNumber("product_idx", Long.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Progress> progress = createEnum("progress", com.supreme.shoekream.model.enumclass.Progress.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<com.supreme.shoekream.model.enumclass.SellBuyStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.SellBuyStatus.class);

    public final EnumPath<com.supreme.shoekream.model.enumclass.Type> type = createEnum("type", com.supreme.shoekream.model.enumclass.Type.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

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

