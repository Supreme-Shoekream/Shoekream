package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.dto.socialDTO.ReplyDTO;

import java.time.LocalDateTime;

public record ReplyApiRequest(
        String content,
        Long boardIdx,
        LocalDateTime createdAt
) {
    public static ReplyApiRequest of(String content, Long boardIdx, LocalDateTime createdAt){
        return new ReplyApiRequest(content, boardIdx, createdAt);
    }

    public ReplyDTO toDTO(MemberDTO memberDTO){
        return ReplyDTO.of(

                memberDTO,
                boardIdx,
                content,
                LocalDateTime.now()
        );
    }
}
