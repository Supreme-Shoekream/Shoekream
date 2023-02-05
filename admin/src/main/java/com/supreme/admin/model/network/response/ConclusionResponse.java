package com.supreme.admin.model.network.response;

import com.supreme.admin.model.dto.ConclusionDTO;

import java.time.LocalDateTime;

public record ConclusionResponse(
        Long idx,
        String proImg,
        Long proIdx,
        String proModelNum,
        String proName,
        String proSize,
        String price,
        LocalDateTime createdAt
) {
    public static ConclusionResponse of(Long idx, String proImg, Long proIdx, String proModelNum, String proName, String proSize, String price, LocalDateTime createdAt){
        return new ConclusionResponse(idx, proImg, proIdx, proModelNum, proName, proSize, price, createdAt);
    }

    public static ConclusionResponse from(ConclusionDTO dto){
        return new ConclusionResponse(
            dto.idx(),
            dto.productDTO().img(),
            dto.productDTO().idx(),
            dto.productDTO().modelNum(),
            dto.productDTO().name(),
            dto.productDTO().size(),
            dto.price(),
            dto.createdAt()
        );
    }
}
