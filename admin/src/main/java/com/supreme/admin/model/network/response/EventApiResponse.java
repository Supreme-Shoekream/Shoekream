package com.supreme.admin.model.network.response;


import com.supreme.admin.model.dto.EventDTO;
import java.time.LocalDateTime;


public record EventApiResponse(
        Long idx,
        String title,
        String img,
        Long productIdx,
        String productName,
        LocalDateTime startTime,
        LocalDateTime endTime

) {
    public static EventApiResponse from(EventDTO dto){
        return new EventApiResponse(
                dto.idx(),
                dto.title(),
                dto.img(),
                dto.productDTO().idx(),
                dto.productDTO().name(),
                dto.startTime(),
                dto.endTime()
        );
    }
}
