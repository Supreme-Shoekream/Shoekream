package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    MEMBER(0, "일반회원", "사용자 가입상태"),
    RESTRICTED(1, "정지", "사용자 탈퇴상태");

    private Integer id;
    private String title;
    private String description;
}