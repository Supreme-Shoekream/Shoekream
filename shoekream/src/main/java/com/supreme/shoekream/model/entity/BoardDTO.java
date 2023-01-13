package com.supreme.shoekream.model.entity;

import java.time.LocalDateTime;
import java.util.List;

public record BoardDTO(
        Long idx,
//        MemberDTO memberDTO,
        String img,

//        List<lkDTO> lkDTOs,
//        List<ReplyDTo> replyDTos,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
