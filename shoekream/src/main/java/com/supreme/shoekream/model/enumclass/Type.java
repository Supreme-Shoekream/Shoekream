package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;


public enum Type {
    SELL_BID("판매입찰"),
    SELL_NOW("즉시판매"),
    BUY_BID("구매입찰"),
    BUY_NOW("즉시구매");


    @Getter private final String description;

    Type(String description){
        this.description=description;
    }
}
