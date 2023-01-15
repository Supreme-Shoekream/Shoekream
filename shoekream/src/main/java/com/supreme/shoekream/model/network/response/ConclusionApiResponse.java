package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.ConclusionDTO;

import java.time.LocalDateTime;

public record ConclusionApiResponse(
        Long idx,
        String price,
        LocalDateTime createdAt
        // product
) {
    public static ConclusionApiResponse of(Long idx, String price, LocalDateTime createdAt){
        return new ConclusionApiResponse(idx, price, createdAt);
    }

    public static ConclusionApiResponse from(ConclusionDTO dto){
        return new ConclusionApiResponse(
                dto.idx(),
                dto.price(),
                dto.createdAt()
        );
    }
}
