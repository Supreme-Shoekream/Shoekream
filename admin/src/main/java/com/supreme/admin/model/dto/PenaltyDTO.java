package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.Penalty;
import com.supreme.admin.model.entity.Sell;
import com.supreme.admin.model.enumclass.Reason;

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
    public Penalty toEntity(Sell sell){
        return Penalty.of(reason, sell, LocalDateTime.now());
    }
}
