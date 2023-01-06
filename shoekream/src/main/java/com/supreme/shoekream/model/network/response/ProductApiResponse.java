package com.supreme.shoekream.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductApiResponse {
    private Long idx;
    private String name;
    private String nameKor;
    private String img;
    private String brand;
    private String size;
    private String category;
    private Long wishCount;
    private String modelNum;
    private String releaseDate;
    private String color;
    private String firstPrice;
}
