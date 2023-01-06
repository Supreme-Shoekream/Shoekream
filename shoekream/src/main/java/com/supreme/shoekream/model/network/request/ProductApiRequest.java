package com.supreme.shoekream.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductApiRequest {
    private String brand;
    private String name;
    private String nameKor;
    private String size;
    private String img;
    private String modelNum;
    private String releaseDate;
    private String color;
    private String firstPrice;
    private String category;
}
