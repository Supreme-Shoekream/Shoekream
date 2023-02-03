package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.Penalty;
import com.supreme.admin.model.enumclass.Reason;

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
}
