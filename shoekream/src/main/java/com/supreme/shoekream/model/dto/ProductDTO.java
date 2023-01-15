package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.response.ProductResponse;

import java.util.List;

public record ProductDTO(
        Long idx,
        String img,
        String brand,
        String name,
        String nameKor,
        String size,
//    private String recentPrice;
//    private String fluctuation;
//    private String nowBuy;
//    private String nowSell;
//    private String wishCount;
        String modelNum,
        String releaseDate,
        String color,
        String firstPrice,
        String category,
        String gender,
        String collection
) {

    public static ProductDTO of(Long idx, String img, String brand, String name, String nameKor, String size, String modelNum, String releaseDate, String color, String firstPrice, String category, String gender, String collection ){
        return new ProductDTO(idx, img, brand, name, nameKor, size, modelNum, releaseDate, color, firstPrice, category, gender, collection);
    }


    // entity -> dto
    public static ProductDTO fromEntity(Product entity){
        return new ProductDTO(
                entity.getIdx(),
                entity.getImg(),
                entity.getBrand(),
                entity.getName(),
                entity.getNameKor(),
                entity.getSize(),
                entity.getModelNum(),
                entity.getReleaseDate(),
                entity.getColor(),
                entity.getFirstPrice(),
                entity.getCategory(),
                entity.getGender(),
                entity.getCollection()
        );
    }

    // dto -> entity 저장 또는 업데이트시
    public Product toEntity(){
        return Product.of(idx, img, brand, name, nameKor, size, modelNum, releaseDate, color, firstPrice, category, gender, collection);
    }
}
