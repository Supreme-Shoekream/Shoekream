package com.supreme.shoekream.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    REGISTERED(0,"가입","사용자 가입상태"),
    UNREGISTERED(1,"탈퇴","사용자 탈퇴상태");

    private Integer idx;
    private String title;
    private String description;


}
