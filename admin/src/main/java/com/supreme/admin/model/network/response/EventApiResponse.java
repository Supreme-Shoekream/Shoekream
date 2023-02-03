package com.supreme.admin.model.network.response;

import com.supreme.admin.model.dto.ProductDTO;

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
