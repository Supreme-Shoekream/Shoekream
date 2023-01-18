package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.enumclass.PointType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public record PointDTO(
        Long idx,
        int point,
        @Enumerated(EnumType.STRING) PointType reason,
        LocalDateTime regDate,
        Long memberIdx

//        MemberDTO memberDTO
) {
    public static PointDTO of(
            Long idx,
            int point,
            PointType reason,
            LocalDateTime regDate,
            Long memberIdx
    ){
        return new PointDTO(idx, point, reason, regDate, memberIdx);
    }
    public static PointDTO from(Point entity){
        return new PointDTO(
                entity.getIdx(),
                entity.getPoint(),
                entity.getReason(),
                entity.getRegDate(),
                entity.getMemberIdx()
//                MemberDTO.fromEntity(entity.getMember()) // MemberDTO

        );
    }
}
