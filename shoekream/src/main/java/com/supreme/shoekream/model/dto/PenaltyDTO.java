package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Penalty;

import com.supreme.shoekream.model.entity.Point;

import com.supreme.shoekream.model.entity.Sell;

import com.supreme.shoekream.model.enumclass.Reason;

import java.time.LocalDateTime;

public record PenaltyDTO(
        Long idx,
        Long sellIdx,
        Reason reason,
        SellDTO sellDTO,
        LocalDateTime createdAt
) {
    public static PenaltyDTO of(Long idx,Long sellIdx, Reason reason, SellDTO sellDTO, LocalDateTime createdAt){
        return new PenaltyDTO(idx,sellIdx, reason,sellDTO, createdAt);
    }

    public static PenaltyDTO fromEntity(Penalty entity){
        return new PenaltyDTO(
                entity.getIdx(),
                entity.getSell().getIdx(),
                entity.getReason(),
                SellDTO.fromEntity(entity.getSell()),
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

    public Penalty toEntity(Sell sell){
        return Penalty.of(reason, sell, LocalDateTime.now());
    }

}
