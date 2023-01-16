package com.supreme.shoekream.model.enumclass;

import lombok.Getter;


public enum OrderStatus {
    BIDDING("입찰중"),         //0
    PROGRESSING("진행중"),     //1
    END("종료");              //2

    @Getter private final String description;
    OrderStatus(String description){
        this.description = description;
    }

}
