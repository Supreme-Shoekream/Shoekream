package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Wish;


public record WishDTO(
        Long idx,
        Member member,
        Long productIdx
) {
    public static WishDTO of(Long idx, Member member, Long productIdx){
        return new WishDTO(idx, member, productIdx);
    }

    public static WishDTO fromEntity(Wish entity) {
        return new WishDTO(
                entity.getIdx(),
                entity.getMember(),
                entity.getProductIdx()
        );
    }
}
