package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;

import java.util.ArrayList;
import java.util.List;

public record ProductWithWishResponse(
        Long idx,
        Member member,
        Product product,
        boolean isWish
) {

    public static ProductWithWishResponse of(Long idx, Member member, Product product, boolean isWish){
        return new ProductWithWishResponse(idx, member, product, isWish);
    }

    public static ProductWithWishResponse fromEntity(Wish wish){
        return ProductWithWishResponse.of(
                wish.getIdx(),
                wish.getMember(),
                wish.getProduct(),
                false
        );
    }

    public static List<ProductWithWishResponse> fromEntity(List<Wish> wishes){
        List<ProductWithWishResponse> products = new ArrayList<>();
        for(int i = 0; i < wishes.size(); i++){
            products.add(ProductWithWishResponse.fromEntity(wishes.get(i)));
        }
        return products;
    }

}
