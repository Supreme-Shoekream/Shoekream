package com.supreme.admin.model.network.response;

import com.supreme.admin.model.dto.PenaltyDTO;
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
        String nickname,
        String memberHp,
        String memberEmail,
        String memAcc,
        String memberStatus,
        Long productIdx,
        String productName,
        String productNameKor,
        String productSize,
        String productModelNum,
        String productReleaseDate,
        Long productFirstPrice,
        Long sellIdx,
        Long sellPrice,
        Long penaltyPrice,
        LocalDateTime createdAt
) {
    public static PenaltyResponse of(Long idx,
                                     String reason,
                                     Long memberIdx,
                                     String memberName,
                                     String nickname,
                                     String memberHp,
                                     String memberEmail,
                                     String memAcc,
                                     String memberStatus,
                                     Long productIdx,
                                     String productName,
                                     String productNameKor,
                                     String productSize,
                                     String productModelNum,
                                     String productReleaseDate,
                                     Long productFirstPrice,
                                     Long sellIdx,
                                     Long sellPrice,
                                     Long penaltyPrice,
                                     LocalDateTime createdAt) {
        return new PenaltyResponse(idx, reason, memberIdx, memberName,nickname, memberHp, memberEmail, memAcc, memberStatus, productIdx, productName, productNameKor,
                productSize, productModelNum, productReleaseDate, productFirstPrice,sellIdx,sellPrice, penaltyPrice,createdAt);
    }

    public static PenaltyResponse from(PenaltyDTO dto) {
        Long sellPrice = dto.sellDTO().price();
        Long penaltyPrice = (long) (Math.floor(sellPrice*0.15/1000)*1000);
        return new PenaltyResponse(
                dto.idx(),
                dto.reason().getDescription(),
                dto.sellDTO().memberDTO().idx(),
                dto.sellDTO().memberDTO().name(),//
                dto.sellDTO().memberDTO().nickname(),
                dto.sellDTO().memberDTO().hp(),//
                dto.sellDTO().memberDTO().email(),//
                dto.sellDTO().memberDTO().accNumber(),
                dto.sellDTO().memberDTO().status().getDescription(),
                dto.sellDTO().productDTO().idx(),
                dto.sellDTO().productDTO().name(),//
                dto.sellDTO().productDTO().nameKor(),
                dto.sellDTO().productDTO().size(),
                dto.sellDTO().productDTO().modelNum(),
                dto.sellDTO().productDTO().releaseDate(),
                dto.sellDTO().productDTO().firstPrice(),
                dto.sellDTO().idx(),
                sellPrice,
                penaltyPrice,
                dto.createdAt()
        );
    }
}