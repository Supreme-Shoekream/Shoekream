package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConclusionLogicService  {
    private final ProductRepository productRepository;

    private ProductApiResponse response(Product product){
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .idx(product.getIdx())
                .name(product.getName())
                .nameKor(product.getNameKor())
                .img(product.getImg())
                .brand(product.getBrand())
                .size(product.getSize())
                .category(product.getCategory())
                .wishCount(product.getWishCount())
                .modelNum(product.getModelNum())
                .releaseDate(product.getReleaseDate())
                .color(product.getColor())
                .firstPrice(product.getFirstPrice())
                .gender(product.getGender())
                .collection(product.getCollection())
                .build();
        return productApiResponse;
    }

//    public Header<List<ProductApiResponse>> search2(Pageable pageable){
//        Page<Product> products = baseRepository.findAll(pageable);
//        List<ProductApiResponse> productApiResponses = products.stream().map(
//                product -> response(product)).collect(Collectors.toList());
//        // collect: 특정 자료 구조 형태에 데이터를 담아달라는 뜻
//        // Collectors.toList(): 리스트형식
//        Pagination pagination = Pagination.builder()
//                .totalPages(products.getTotalPages())
//                .totalElements(products.getTotalElements())
//                .currentPage(products.getNumber())
//                .currentElements(products.getNumberOfElements())
//                .build();
//        return Header.OK(productApiResponses, pagination);
//    }

    @Transactional(readOnly=true)
    public List<Product> list(){
        System.out.println(productRepository.findAll());
        return productRepository.findAll();
    }
}
