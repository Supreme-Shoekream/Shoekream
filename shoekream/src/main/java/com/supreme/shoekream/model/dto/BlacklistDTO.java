package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Blacklist;

import java.time.LocalDateTime;

public record BlacklistDTO(
        Long idx,
        String reason,
        Long memberIdx,
        LocalDateTime createdAt
) {
    public static BlacklistDTO of(Long idx, String reason, Long memberIdx, LocalDateTime createdAt){
        return new BlacklistDTO(idx, reason, memberIdx, createdAt);
    }

    public static BlacklistDTO fromEntity(Blacklist entity){
        return new BlacklistDTO(
                entity.getIdx(),
                entity.getReason(),
                entity.getMemberIdx(),
                entity.getCreatedAt()
        );
    }

}
