package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;

import java.time.LocalDateTime;

public record ConclusionDTO(
        Long idx,
        String price,
        LocalDateTime createdAt,
        ProductDTO productDTO
) {
    public static ConclusionDTO of(Long idx, String price, LocalDateTime createdAt, ProductDTO productDTO){
        return new ConclusionDTO(idx, price, createdAt, productDTO);
    }
    public static ConclusionDTO of(String price, LocalDateTime createdAt, ProductDTO productDTO){
        return new ConclusionDTO(null, price, createdAt, productDTO);
    }

    public static ConclusionDTO fromEntity(Conclusion entity){
        return new ConclusionDTO(
                entity.getIdx(),
                entity.getPrice(),
                entity.getCreatedAt(),
                ProductDTO.fromEntity(entity.getProduct())
        );
    }

    public Conclusion toEntity(Product product){
        return Conclusion.of(product,price,createdAt);
    }
}
