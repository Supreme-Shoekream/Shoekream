package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.socialDTO.LkDTO;
import com.supreme.shoekream.model.dto.socialDTO.ReplyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardApiResponse {
    Long idx;
    String img;
    String content;
    String hashtag;

    MemberDTO memberDTO;
    List<LkDTO> lkDTOS;
    List<ReplyDTO> replyDTOS;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
