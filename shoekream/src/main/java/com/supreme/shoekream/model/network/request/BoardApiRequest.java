package com.supreme.shoekream.model.network.request;


import com.supreme.shoekream.model.entity.Member;

import java.time.LocalDateTime;

public record BoardApiRequest(
        Long idx,
        Member member,
        String img,
        LocalDateTime createAt,
        LocalDateTime modifiedAt
) {

}
