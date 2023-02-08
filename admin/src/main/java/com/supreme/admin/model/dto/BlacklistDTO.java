package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.Blacklist;
import com.supreme.admin.model.entity.Member;

import java.time.LocalDateTime;

public record BlacklistDTO(
        Long idx,
        String reason,
        MemberDTO memberDTO,
        LocalDateTime createdAt
) {
    public static BlacklistDTO of(Long idx, String reason){
        return new BlacklistDTO(idx, reason, null, null);
    }

    public static BlacklistDTO fromEntity(Blacklist entity){
        return new BlacklistDTO(
                entity.getIdx(),
                entity.getReason(),
                MemberDTO.fromEntity(entity.getMember()),
                entity.getCreatedAt()
        );
    }
    public Blacklist toEntity(Member member){
        return Blacklist.of(
                idx,
                reason,
                member
        );
    }

}

