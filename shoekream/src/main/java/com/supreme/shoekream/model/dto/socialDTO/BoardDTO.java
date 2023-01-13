package com.supreme.shoekream.model.dto.socialDTO;

import java.time.LocalDateTime;
import java.util.List;

public record BoardDTO(
        Long idx,
//        MemberDTO memberDTO,
        String img,

        List<LkDTO> lkDTOs,
        List<ReplyDTO> replyDTOs,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
