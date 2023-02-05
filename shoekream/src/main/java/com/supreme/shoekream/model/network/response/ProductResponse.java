package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.ProductDTO;

public record ProductResponse(
        Long idx,
        String name,
        String nameKor,
        String img,
        String brand,
        String size,
        int wishCount,
        String modelNum,
        String releaseDate,
        String color,
        Long firstPrice
) {
    public static ProductResponse of(Long idx, String name, String nameKor, String img, String brand, String size, int wishCount, String modelNum, String releaseDate, String color, Long firstPrice) {
        return new ProductResponse(idx, name, nameKor, img, brand, size, wishCount, modelNum, releaseDate, color, firstPrice);
    }

    public static ProductResponse from(ProductDTO dto){
        return new ProductResponse(
                dto.idx(),
                dto.name(),
                dto.nameKor(),
                dto.brand(),
                dto.img(),
                dto.size(),
                dto.wishCount(),
                dto.modelNum(),
                dto.releaseDate(),
                dto.color(),
                dto.firstPrice()
        );
    }

}
