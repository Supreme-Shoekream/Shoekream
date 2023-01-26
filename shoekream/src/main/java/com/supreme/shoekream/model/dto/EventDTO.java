package com.supreme.shoekream.model.dto;

import java.time.LocalDateTime;

public record EventDTO(
        Long idx,
        String title,
        String img,

        ProductDTO productDTO,
        LocalDateTime startTime,
        LocalDateTime endTime
) {

    public static EventDTO of(
            Long idx,
            String title,
            String img,

            ProductDTO productDTO,
            LocalDateTime startTime,
            LocalDateTime endTime
    ){
        return new EventDTO(idx, title, img, productDTO, startTime, endTime);
    }
}
