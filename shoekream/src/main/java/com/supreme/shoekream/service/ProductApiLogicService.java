package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductApiLogicService extends BaseService<ProductApiRequest, ProductApiResponse, Product> {
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
                .build();
        return productApiResponse;
    }

    @Override
    public Header<ProductApiResponse> create(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Product product = Product.builder()
                .name(productApiRequest.getName())
                .nameKor(productApiRequest.getNameKor())
                .img(productApiRequest.getImg())
                .brand(productApiRequest.getBrand())
                .size(productApiRequest.getSize())
                .category(productApiRequest.getCategory())
                .modelNum(productApiRequest.getModelNum())
                .releaseDate(productApiRequest.getReleaseDate())
                .color(productApiRequest.getColor())
                .firstPrice(productApiRequest.getFirstPrice())
                .build();
        Product newProduct = baseRepository.save(product);

        return Header.OK(response(newProduct));
    }

    @Override
    public Header<ProductApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<ProductApiResponse> update(Header<ProductApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long idx) {
        return null;
    }

    public Header<List<ProductApiResponse>> search(Pageable pageable){
        Page<Product> products = baseRepository.findAll(pageable);
        List<ProductApiResponse> productApiResponses = products.stream().map(
                product -> response(product)).collect(Collectors.toList());
        // collect: 특정 자료 구조 형태에 데이터를 담아달라는 뜻
        // Collectors.toList(): 리스트형식
        Pagination pagination = Pagination.builder()
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .currentPage(products.getNumber())
                .currentElements(products.getNumberOfElements())
                .build();
        return Header.OK(productApiResponses, pagination);
    }

}
