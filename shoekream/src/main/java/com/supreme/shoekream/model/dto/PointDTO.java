package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.enumclass.PointType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public record PointDTO(
        Long idx,
        Long point,
        PointType reason,
        LocalDateTime regDate

//        MemberDTO memberDTO
) {
    public static PointDTO of(
            Long idx,
            Long point,
            PointType reason,
            LocalDateTime regDate
    ){
        return new PointDTO(idx, point, reason, regDate);
    }
    public static PointDTO of(
            Long point,
            PointType reason,
            LocalDateTime regDate
    ){
        return new PointDTO(null, point, reason, regDate);
    }
    public static PointDTO from(Point entity){
        return new PointDTO(
                entity.getIdx(),
                entity.getPoint(),
                entity.getReason(),
                entity.getRegDate()
//                MemberDTO.fromEntity(entity.getMember()) // MemberDTO

        );
    }
    public Point toEntity(Member member){
        return Point.of(
                point,
                reason,
                regDate,
                member
        );
    }
}
