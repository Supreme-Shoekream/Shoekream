package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = -1497871974L;

    public static final QAdmin admin = new QAdmin("admin");

    public final com.supreme.shoekream.model.config.QBaseEntity _super = new com.supreme.shoekream.model.config.QBaseEntity(this);

    public final StringPath adminid = createString("adminid");

    public final StringPath adminpw = createString("adminpw");

    public final StringPath hp = createString("hp");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<com.supreme.shoekream.model.enumclass.UserStatus> status = createEnum("status", com.supreme.shoekream.model.enumclass.UserStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

