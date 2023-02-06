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
        Long productFirstPrice,
        LocalDateTime createdAt
) {
    public static PenaltyResponse of(Long idx, String reason, Long memberIdx, String memberName, String memberHp, String memberEmail, String memberStatus,
                                     Long productIdx, String productName, String productNameKor, String productSize, String productModelNum, String productReleaseDate, Long productFirstPrice, LocalDateTime createdAt) {
        return new PenaltyResponse(idx, reason, memberIdx, memberName, memberHp, memberEmail, memberStatus, productIdx, productName, productNameKor,
                productSize, productModelNum, productReleaseDate, productFirstPrice, createdAt);
    }

    public static PenaltyResponse from(PenaltyDTO dto) {
        return new PenaltyResponse(
                dto.idx(),
                dto.reason().getDescription(),
                dto.sellDTO().memberDTO().idx(),
                dto.sellDTO().memberDTO().name(),
                dto.sellDTO().memberDTO().hp(),
                dto.sellDTO().memberDTO().email(),
                dto.sellDTO().memberDTO().status().getDescription(),
                dto.sellDTO().productDTO().idx(),
                dto.sellDTO().productDTO().name(),
                dto.sellDTO().productDTO().nameKor(),
                dto.sellDTO().productDTO().size(),
                dto.sellDTO().productDTO().modelNum(),
                dto.sellDTO().productDTO().releaseDate(),
                dto.sellDTO().productDTO().firstPrice(),
                dto.createdAt()
        );
    }
}