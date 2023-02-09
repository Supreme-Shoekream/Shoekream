package com.supreme.admin.service;


import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.response.ProductApiResponse;
import com.supreme.admin.repository.ProductRepository;
import com.supreme.admin.repository.WishRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductApiLogicService {
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    private ProductApiResponse response(Product product){
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .idx(product.getIdx())
                .img(product.getImg())
                .brand(product.getBrand())
                .name(product.getName())
                .nameKor(product.getNameKor())
                .size(product.getSize())
                .wishCount(product.getWishCount())
                .modelNum(product.getModelNum())
                .releaseDate(product.getReleaseDate())
                .color(product.getColor())
                .firstPrice(product.getFirstPrice())
                .category(product.getCategory())
                .gender(product.getGender())
                .collection(product.getCollection())
                .build();
        return productApiResponse;
    }

    public Header<ProductApiResponse> read(Long idx) {
        return productRepository.findById(idx)
                .map(product-> response(product))
                .map(Header::OK).orElseGet(() -> Header.ERROR("상품 없음!"));
    }


}
