package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 189246586L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final QBrand brand;

    public final StringPath category = createString("category");

    public final StringPath color = createString("color");

    public final ListPath<Conclusion, QConclusion> conclusions = this.<Conclusion, QConclusion>createList("conclusions", Conclusion.class, QConclusion.class, PathInits.DIRECT2);

    public final StringPath firstPrice = createString("firstPrice");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath img = createString("img");

    public final StringPath modelNum = createString("modelNum");

    public final StringPath name = createString("name");

    public final StringPath nameKor = createString("nameKor");

    public final StringPath releaseDate = createString("releaseDate");

    public final StringPath size = createString("size");

    public final NumberPath<Long> wishCount = createNumber("wishCount", Long.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new QBrand(forProperty("brand")) : null;
    }

}

