package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Tag;

public record BoardStyleApiRequest(
        Long idx,
        String content,
        String img,
        String hashtag
//        Long memberIdx,
//        Long tagIdx
) {
}
