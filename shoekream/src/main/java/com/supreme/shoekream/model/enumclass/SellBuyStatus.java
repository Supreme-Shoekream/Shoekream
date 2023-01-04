package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SellBuyStatus {
    BIDDING(0, "입찰중", "판매/구매 상태: 입찰중"),
    EXPIRED(1, "입찰기한만료", "판매/구매 상태: 입찰 기한만료"),
    PROGRESSING(2, "진행중", "판매/구매 상태: 진행중"),
    END(3, "종료", "판매/구매 상태: 종료");

    private Integer id;
    private String title;
    private String description;
}
