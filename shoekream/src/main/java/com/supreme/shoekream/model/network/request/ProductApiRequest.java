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
    private String name;
    private String nameKor;
    private String size;
    private String color;
    private String firstPrice;
}
