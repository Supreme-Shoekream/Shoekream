package com.supreme.shoekream.model.enumclass;

import lombok.Getter;

public enum SearchType {
    CATEGORY("카테고리"),
    BRAND("브랜드"),
    COLLECTION("컬렉션"),
    GENDER("성별"),
    FIRSTPRICE("발매가");

    @Getter private final String description;

    SearchType(String description) {this.description = description;
    }
}