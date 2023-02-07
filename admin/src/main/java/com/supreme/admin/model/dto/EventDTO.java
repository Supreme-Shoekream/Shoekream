package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record EventDTO(
        Long idx,
        ProductDTO productDTO,
        String title,
        String img,


        List<EventMemberDTO> eventMemberDTO,
        LocalDateTime startTime,
        LocalDateTime endTime
) {

    public static EventDTO of(
            Long idx,
            ProductDTO productDTO,
            String title,
            String img,


            List<EventMemberDTO> eventMemberDTO,
            LocalDateTime startTime,
            LocalDateTime endTime
    ){
        return new EventDTO(idx,  productDTO, title, img,eventMemberDTO, startTime, endTime);
    }
    public static EventDTO of(
            ProductDTO productDTO,
            String title,
            String img,
            LocalDateTime startTime,
            LocalDateTime endTime
    ){
        return new EventDTO(null,productDTO, title, img, null, startTime, endTime);
    }
    public static EventDTO fromEntity(EventProduct entity){
        return new EventDTO(
                entity.getIdx(),
                ProductDTO.fromEntity(entity.getProduct()),
                entity.getTitle(),
                entity.getImg(),
                EventMemberDTO.fromEntity(entity.getEventMember()),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public static List<EventDTO> fromEntity(List<EventProduct> eventProducts){
        List<EventDTO> events = new ArrayList<>();
        for(int i=0;i<eventProducts.size();i++){
            events.add(EventDTO.fromEntity(eventProducts.get(i)));
        }
        return events;
    }

    public EventProduct toEntity(Product product){
        return EventProduct.of(
                product,
                title,
                img,
                startTime,
                endTime
        );
    }
}

