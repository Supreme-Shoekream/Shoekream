package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Blacklist;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.enumclass.Reason;

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
