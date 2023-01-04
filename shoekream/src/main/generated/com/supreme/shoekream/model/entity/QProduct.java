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

    public final NumberPath<Long> brand_idx = createNumber("brand_idx", Long.class);

    public final StringPath category_idx = createString("category_idx");

    public final StringPath color = createString("color");

    public final StringPath create_by = createString("create_by");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath img = createString("img");

    public final StringPath model_num = createString("model_num");

    public final StringPath name = createString("name");

    public final StringPath name_kor = createString("name_kor");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath release_date = createString("release_date");

    public final StringPath size = createString("size");

    public final StringPath update_by = createString("update_by");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final NumberPath<Long> wish_count = createNumber("wish_count", Long.class);

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

