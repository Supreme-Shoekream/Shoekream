package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.network.response.ProductResponse;

public record ProductDTO(
        Long idx,
        String name,
        String nameKor,
        String img,
        String brand,
        String size,
        Long wishCount,
        String modelNum,
        String releaseDate,
        String color,
        String firstPrice
) {
//    public static ProductResponse from(ProductDTO dto){
//        return new ProductResponse(
//                dto.idx(),
//                dto.img()
//        );
//    }
}
