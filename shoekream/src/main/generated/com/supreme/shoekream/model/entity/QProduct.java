package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 189246586L;

    public static final QProduct product = new QProduct("product");

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    public final NumberPath<Long> brandIdx = createNumber("brandIdx", Long.class);

    public final StringPath categoryIdx = createString("categoryIdx");

    public final StringPath color = createString("color");

    public final StringPath createBy = createString("createBy");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath firstPrice = createString("firstPrice");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath img = createString("img");

    public final StringPath modelNum = createString("modelNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nameKor = createString("nameKor");

    public final StringPath releaseDate = createString("releaseDate");

    public final StringPath size = createString("size");

    public final StringPath updateBy = createString("updateBy");

    public final NumberPath<Long> wishCount = createNumber("wishCount", Long.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

