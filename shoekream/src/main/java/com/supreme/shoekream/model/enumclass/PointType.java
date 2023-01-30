package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PointType {
    EVENT_WINNINGS(0, "이벤트 당첨", "100P"),
    EVENT_ACCOUNT(1, "계좌 등록", "100P"),
    EVENT_BIRTHDAY(2, "생일", "100P"),
    EVENT_EXCELLENT(3, "우수회원", "100P"),
    EVENT_USE(4, "사용", "-3000P");

    private Integer id;
    private String reason;
    private String description;
}