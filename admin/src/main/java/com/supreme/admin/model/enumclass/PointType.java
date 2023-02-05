package com.supreme.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PointType {
    EVENT_WINNINGS(0, "이벤트 당첨", "100P"),
    EVENT_ACCOUNT(1, "계좌 등록", "100P"),
    EVENT_BIRTHDAY(2, "생일", "100P"),
    EVENT_EXCELLENT(3, "우수회원", "100P"),
    EVENT_USE(4, "이벤트참여", "-1000P"),
    BUY_USE(5,"사용","사용자가 구매시 사용한 포인트");

    private Integer id;
    private String reason;
    private String description;
}