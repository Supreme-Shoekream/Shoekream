package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Reason {
    DELAYED_SHIPMENT(0, "발송지연"),
    REFUSED(1, "판매거부"),
    OUT_OF_STOCK(2, "미입고"),
    INSPECTION_FAILED(3, "검수기준 악용");

    private Integer id;
    private String description;
}
