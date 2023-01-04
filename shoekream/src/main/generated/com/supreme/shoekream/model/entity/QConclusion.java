package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConclusion is a Querydsl query type for Conclusion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConclusion extends EntityPathBase<Conclusion> {

    private static final long serialVersionUID = -636222796L;

    public static final QConclusion conclusion = new QConclusion("conclusion");

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    public final NumberPath<Long> buy_idx = createNumber("buy_idx", Long.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> sell_idx = createNumber("sell_idx", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QConclusion(String variable) {
        super(Conclusion.class, forVariable(variable));
    }

    public QConclusion(Path<? extends Conclusion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConclusion(PathMetadata metadata) {
        super(Conclusion.class, metadata);
    }

}

