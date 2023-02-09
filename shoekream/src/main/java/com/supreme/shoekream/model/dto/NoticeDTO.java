package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Notice;

import java.time.LocalDateTime;

public record NoticeDTO(
        Long idx,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public Notice toEntity(){
        return Notice.of(
                title,
                content,
                createdAt,
                modifiedAt
        );
    }
    public static NoticeDTO of(
            Long idx,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ){
        return new NoticeDTO(idx,title, content, createdAt, modifiedAt);
    }

    public static NoticeDTO fromEntity(Notice entity){
        return new NoticeDTO(
                entity.getIdx(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

}
