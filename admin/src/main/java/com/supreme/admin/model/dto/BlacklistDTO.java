package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.Blacklist;
import com.supreme.admin.model.enumclass.Reason;

import java.time.LocalDateTime;

public record BlacklistDTO(
        Long idx,
        Reason reason,
        MemberDTO memberDTO,
        LocalDateTime createdAt
) {
    public static BlacklistDTO of(Long idx, Reason reason, MemberDTO memberDTO, LocalDateTime createdAt){
        return new BlacklistDTO(idx, reason, memberDTO, createdAt);
    }

    public static BlacklistDTO fromEntity(Blacklist entity){
        return new BlacklistDTO(
                entity.getIdx(),
                entity.getReason(),
                MemberDTO.fromEntity(entity.getMember()),
                entity.getCreatedAt()
        );
    }

}
