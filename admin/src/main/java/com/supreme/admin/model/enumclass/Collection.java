package com.supreme.admin.model.enumclass;

import lombok.Getter;

public enum Collection {
    JUST_DROP("발매상품"),
    MOST_POPULAR("인기상품"),
    NEW_IN("신규등록상품"),
    SHOPPIING_WISELY("정가보다 저렴한 가격"),
    BRAND_MD_PICK("브랜드관 베스트 아이템"),
    SIMPLE_CHIC("심플한 로고 플레이의 매력"),
    BEST_APPAREL_COLLABS("의류에도 불었던 콜라보 열풍"),
    NEW_LOWEST_ASKS("새로운 즉시 구매가"),
    NEW_HIGHEST_BIDS("새로운 즉시 판매가"),
    UPCOMING_RELEASE("발매예정"),
    BEST_COLLECTIBLES("키덜트들 취향저격 200%"),
    BEST_JEWELRY_WATCHES("올해를 빛내준 액세서리"),
    BEST_CAMPING_GEAR("캠핑의 매력에 빠진 한해");

    @Getter
    private final String description;

    Collection(String description){
        this.description=description;
    }
}
