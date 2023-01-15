package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.entity.Conclusion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductWithConclusionApiResponse {
    private Long idx;
    private String img;
    private String brand;
    private String name;
    private String nameKor;
    private String size;
    private String modelNum;
    private String releaseDate;
    private String color;
    private String firstPrice;
    List<Conclusion> conclusions;
}
