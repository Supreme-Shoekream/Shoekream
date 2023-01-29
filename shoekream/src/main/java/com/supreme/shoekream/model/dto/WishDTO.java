package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.*;
import lombok.Builder;

@Builder
public record WishDTO(
        Long idx,
        Long memberIdx,
        Long productIdx
) {
    public static WishDTO of(Long idx, Long memberIdx, Long productIdx){
        return new WishDTO(idx, memberIdx, productIdx);
    }

    public static WishDTO fromEntity(Wish entity) {
        return new WishDTO(
            entity.getIdx(),
            entity.getMember().getIdx(),
            entity.getProduct().getIdx()
        );
    }

//    public Wish toEntity(Long member, Long product){
//        return Wish.of(
//                member,
//                product
//        );
//    }
}
