package com.supreme.admin.model.dto;

import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Notice;
import com.supreme.admin.model.entity.Product;

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
}
