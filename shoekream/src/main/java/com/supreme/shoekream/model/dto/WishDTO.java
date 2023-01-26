package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Wish;

public record WishDTO(
        Long idx,
        Member member,
        ProductDTO productDTO
) {
    public static WishDTO of(Long idx, Member member, ProductDTO productDTO){
        return new WishDTO(idx, member, productDTO);
    }

    public static WishDTO fromEntity(Wish entity) {
        return new WishDTO(
            entity.getIdx(),
            entity.getMember(),
            ProductDTO.fromEntity(entity.getProduct())
        );
    }
}
