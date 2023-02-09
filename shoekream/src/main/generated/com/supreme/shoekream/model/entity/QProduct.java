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

    public final StringPath brand = createString("brand");

    public final StringPath category = createString("category");

    public final StringPath collection = createString("collection");

    public final StringPath color = createString("color");

    public final NumberPath<Long> firstPrice = createNumber("firstPrice", Long.class);

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath img = createString("img");

    public final StringPath modelNum = createString("modelNum");

    public final StringPath name = createString("name");

    public final StringPath nameKor = createString("nameKor");

    public final StringPath releaseDate = createString("releaseDate");

    public final StringPath size = createString("size");

    public final NumberPath<Integer> wishCount = createNumber("wishCount", Integer.class);

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

