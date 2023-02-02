package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.request.WishApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.WishApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Product> brandOtherProduct(String brandName){
        return productRepository.findBrandByBrand(brandName).stream()
                .map(ProductDTO::fromEntity2).collect(Collectors.toCollection(LinkedList::new));
    }


}
