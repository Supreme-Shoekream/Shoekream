package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.*;

import java.time.LocalDateTime;


public record EventApiRequest(
        Long idx,
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
