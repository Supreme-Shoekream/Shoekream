package com.supreme.admin.model.network.request;


import com.supreme.admin.model.entity.Member;

import java.time.LocalDateTime;

public record BoardApiRequest(
        Long idx,
        Member member,
        String img,
        LocalDateTime createAt,
        LocalDateTime modifiedAt
) {

}
