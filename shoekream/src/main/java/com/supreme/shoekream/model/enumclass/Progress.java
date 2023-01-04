package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Progress {
    SHIPMENT_COMPLETE(0, "발송완료", "발송완료시 판매자/구매자의 진행상황"),
    RECEIVING_COMPLETE(1, "입고완료", "입고완료시 판매자/구매자의 진행상황"),
    EXAMINATION_PASS(2, "검수합격", "검수합격시 판매자/구매자의 진행상황"),
    DELIVERY_COMPLETE(3, "배송완료", "거래완료시 구매자의 진행상황"),
    CALCULATE_COMPLETE(4, "정산완료", "거래완료시 판매자의 진행상황"),
    TRANSACTION_FAIL(5, "거래실패", "거래실패시 판매자/구매자의 진행상황"),
    CANCLE_COMPLETE(6, "취소완료", "취소완료시 판매자/구매자의 진행상황");

    private Integer id;
    private String title;
    private String description;
}
