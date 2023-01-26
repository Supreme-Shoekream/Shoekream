package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.ProductDTO;

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
