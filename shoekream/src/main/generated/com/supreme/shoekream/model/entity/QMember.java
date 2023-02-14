package com.supreme.shoekream.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1155075503L;

    public static final QMember member = new QMember("member1");

    public final StringPath accNumber = createString("accNumber");

    public final StringPath bank = createString("bank");

    public final StringPath email = createString("email");

    public final StringPath hp = createString("hp");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath memberPw = createString("memberPw");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final StringPath profileMemo = createString("profileMemo");

    public final StringPath shoeSize = createString("shoeSize");

    public final EnumPath<com.supreme.shoekream.model.enumclass.Status> status = createEnum("status", com.supreme.shoekream.model.enumclass.Status.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

