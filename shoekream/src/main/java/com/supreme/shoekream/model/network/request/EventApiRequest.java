package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.*;

import java.time.LocalDateTime;


public record EventApiRequest(
        Long productIdx,
        String title,
        String img,
        LocalDateTime startTime,
        LocalDateTime endTime

) {
    public EventDTO toDTO(ProductDTO productDTO){
        return EventDTO.of(
                productDTO,
                title,
                img,
                startTime,
                endTime
        );
    }
}
