package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;


public enum Type {
    SELL_BID("판매입찰"),   //0
    SELL_NOW("즉시판매"),   //1
    BUY_BID("구매입찰"),    //2
    BUY_NOW("즉시구매");    //3


    @Getter private final String description;

    Type(String description){
        this.description=description;
    }
}
