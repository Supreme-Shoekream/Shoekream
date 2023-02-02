package com.supreme.shoekream.repository.querydsl;

import java.util.List;

public interface ProductRepositoryCustom {
    List<String> findAllDistinctBrands();
}
