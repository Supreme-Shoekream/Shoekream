package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.ConclusionDTO;

import java.time.LocalDateTime;

public record ConclusionResponse(
        Long idx,
        String price,
        LocalDateTime createdAt,
        String productSize
) {
    public static ConclusionResponse of(Long idx, String price, LocalDateTime createdAt, String productSize){
        return new ConclusionResponse(idx, price, createdAt, productSize);
    }

    public static ConclusionResponse from(ConclusionDTO dto){
        return new ConclusionResponse(
            dto.idx(),
            dto.price(),
            dto.createdAt(),
            dto.productDTO().size()
        );
    }
}
