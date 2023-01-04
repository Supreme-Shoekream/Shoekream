package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Period {
    ONE_DAY(0, "1일", "입찰 마감 기한: 1일"),
    THREE_DAY(1, "3일", "입찰 마감 기한: 3일"),
    ONE_WEEK(2, "7일", "입찰 마감 기한: 7일"),
    ONE_MONTH(3, "30일", "입찰 마감 기한: 30일"),
    TWO_MONTH(4, "60일", "입찰 마감 기한: 60일");

    private Integer id;
    private String title;
    private String description;
}
