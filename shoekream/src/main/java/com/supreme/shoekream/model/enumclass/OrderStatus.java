package com.supreme.shoekream.model.enumclass;

import lombok.Getter;


public enum OrderStatus {
    BIDDING("입찰중"),
    EXPIRED("입찰기한만료"),
    PROGRESSING("진행중"),
    END("종료");

    @Getter private final String description;
    OrderStatus(String description){
        this.description = description;
    }

}
