package com.supreme.shoekream.model.enumclass;

import lombok.Getter;

@Getter
public enum SellProgress {
    SHIPMENT_REQUEST("발송요청", "거래채결시 판매자 진행상황"),
    RECEIVING_COMPLETE("입고완료", "입고완료시 판매자 진행상황"),
    EXAMINATION_PASS("검수합격", "검수합격시 판매자 진행상황"),
    CALCULATE_COMPLETE("정산완료", "거래완료시 판매자 진행상황");


    @Getter private final String title;
    @Getter private final String description;

    SellProgress(String title, String description){
        this.title = title;
        this.description=description;
    }
}
