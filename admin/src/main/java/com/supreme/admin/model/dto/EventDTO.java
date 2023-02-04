package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.*;

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
    public static EventDTO fromEntity(EventProduct entity){
        return new EventDTO(
                entity.getIdx(),
                entity.getTitle(),
                entity.getImg(),
                ProductDTO.fromEntity(entity.getProduct()),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
    public EventProduct toEntity(Product product){
        return EventProduct.of(idx,title,img,product,startTime,endTime);
    }
}
