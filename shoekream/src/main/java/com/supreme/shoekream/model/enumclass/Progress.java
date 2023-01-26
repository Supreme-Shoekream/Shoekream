package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Progress {
    SHIPMENT_COMPLETE(0, "발송완료", "발송완료시 구매자의 진행상황"),
    RECEIVING_COMPLETE(1,"입고완료", "입고완료시 구매자의 진행상황"),
    EXAMINATION_PASS(2, "검수합격", "검수합격시 구매자의 진행상황"),
    DELIVERY_COMPLETE(3, "배송완료", "거래완료시 구매자의 진행상황");

    @Getter private final Integer idx;
    @Getter private final String title;
    @Getter private final String description;

    Progress(Integer idx, String title, String description){
        this.idx = idx;
        this.title = title;
        this.description=description;
    }

}
