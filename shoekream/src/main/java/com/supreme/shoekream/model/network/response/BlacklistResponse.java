package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.BlacklistDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record BlacklistResponse(
        Long idx,
        String reason,
        Long memberIdx,
        String memberName,
        String memberHp,
        String memberEmail,
        String memberStatus,
        LocalDateTime createdAt
) {
    public static BlacklistResponse of(Long idx, String reason, Long memberIdx, String memberName, String memberHp, String memberEmail,
                                       String memberStatus, LocalDateTime createdAt){
        return new BlacklistResponse(idx, reason, memberIdx, memberName, memberHp, memberEmail, memberStatus, createdAt);
    }
    public static BlacklistResponse from(BlacklistDTO dto){

        return new BlacklistResponse(
                dto.idx(),
                dto.reason(),
                dto.memberDTO().idx(),
                dto.memberDTO().name(),
                dto.memberDTO().hp(),
                dto.memberDTO().email(),
                dto.memberDTO().status().getDescription(),
                dto.createdAt()
        );
    }
}
