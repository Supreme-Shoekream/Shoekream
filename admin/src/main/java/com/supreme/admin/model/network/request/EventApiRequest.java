package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.ProductDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventApiRequest(
        Long idx,
        String title,
        String img,
        ProductDTO productDTO,
        LocalDateTime startTime,
        LocalDateTime endTime

) {

}
