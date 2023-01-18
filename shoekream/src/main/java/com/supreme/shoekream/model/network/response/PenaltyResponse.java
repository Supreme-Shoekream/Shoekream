package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.PenaltyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record PenaltyResponse(
        Long idx,
        String reason,
        Long memberIdx,
        String memberName,
        String memberHp,
        String memberEmail,
        String memberStatus,
        Long productIdx,
        String productName,
        String productNameKor,
        String productSize,
        String productModelNum,
        String productReleaseDate,
        String productFirstPrice,
        LocalDateTime createdAt
) {
    public static PenaltyResponse of(Long idx, String reason, Long memberIdx, String memberName, String memberHp, String memberEmail, String memberStatus,
                                     Long productIdx, String productName, String productNameKor, String productSize, String productModelNum, String productReleaseDate, String productFirstPrice, LocalDateTime createdAt) {
        return new PenaltyResponse(idx, reason, memberIdx, memberName, memberHp, memberEmail, memberStatus, productIdx, productName, productNameKor,
                productSize, productModelNum, productReleaseDate, productFirstPrice, createdAt);
    }

    public static PenaltyResponse from(PenaltyDTO dto) {
        return new PenaltyResponse(
                dto.idx(),
                dto.reason().getDescription(),
                dto.memberDTO().idx(),
                dto.memberDTO().name(),
                dto.memberDTO().hp(),
                dto.memberDTO().email(),
                dto.memberDTO().status().getDescription(),
                dto.productDTO().idx(),
                dto.productDTO().name(),
                dto.productDTO().nameKor(),
                dto.productDTO().size(),
                dto.productDTO().modelNum(),
                dto.productDTO().releaseDate(),
                dto.productDTO().firstPrice(),
                dto.createdAt()
        );
    }
}