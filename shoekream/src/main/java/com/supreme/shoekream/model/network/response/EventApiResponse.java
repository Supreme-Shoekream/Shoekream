package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.ProductDTO;

import java.time.LocalDateTime;

public record EventApiResponse(
        Long idx,
        String title,
        String img,
        ProductDTO productDTO,
        LocalDateTime startTime,
        LocalDateTime endTime

) {
}
