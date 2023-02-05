package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Penalty;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.enumclass.Reason;

import java.time.LocalDateTime;

public record PenaltyDTO(
        Long idx,
        Reason reason,
        MemberDTO memberDTO,
        ProductDTO productDTO,
        LocalDateTime createdAt
) {
    public static PenaltyDTO of(Long idx, Reason reason, MemberDTO memberDTO, ProductDTO productDTO, LocalDateTime createdAt){
        return new PenaltyDTO(idx, reason, memberDTO, productDTO, createdAt);
    }

    public static PenaltyDTO fromEntity(Penalty entity){
        return new PenaltyDTO(
                entity.getIdx(),
                entity.getReason(),
                MemberDTO.fromEntity(entity.getMember()),
                ProductDTO.fromEntity(entity.getProduct()),
                entity.getCreatedAt()
        );
    }

//    public Penalty toEntity(Member member){
//        return Penalty.of(
//                idx,
//                reason,
//                member,
//                product,
//                createdAt
//        );
//    }
}
