package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    SELL_BID(0, "판매입찰", "판매방법: 입찰"),
    SELL_NOW(1, "즉시판매", "판매방법: 즉시"),
    BUY_BID(2, "구매입찰", "구매방법: 입찰"),
    BUY_NOW(3, "즉시구매", "구매방법: 즉시");

    private Integer id;
    private String title;
    private String description;
}
