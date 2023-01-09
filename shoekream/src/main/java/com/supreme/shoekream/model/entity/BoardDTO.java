package com.supreme.shoekream.model.entity;

import java.time.LocalDateTime;

public record BoardDTO(
        Long idx,
        Member member,
        String img,

        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
