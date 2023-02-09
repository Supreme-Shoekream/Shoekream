package com.supreme.admin.service;

import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.Pagination;
import com.supreme.admin.model.network.request.ProductApiRequest;
import com.supreme.admin.model.network.response.ProductApiResponse;
import com.supreme.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductApiLogicService extends BaseService<ProductApiRequest, ProductApiResponse, Product> {
    private final ProductRepository productRepository;

    private ProductApiResponse response(Product product){
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .idx(product.getIdx())
                .img(product.getImg())
                .brand(product.getBrand())
                .name(product.getName())
                .nameKor(product.getNameKor())
                .size(product.getSize())
                .modelNum(product.getModelNum())
                .releaseDate(product.getReleaseDate())
                .color(product.getColor())
                .firstPrice(product.getFirstPrice())
                .gender(product.getGender())
                .collection(product.getCollection())
                .category(product.getCategory())
                .build();
        return productApiResponse;
    }

    @Override
    public Header<ProductApiResponse> create(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Product product = Product.builder()
                .img(productApiRequest.getImg())
                .brand(productApiRequest.getBrand())
                .name(productApiRequest.getName())
                .nameKor(productApiRequest.getNameKor())
                .size(productApiRequest.getSize())
                .modelNum(productApiRequest.getModelNum())
                .releaseDate(productApiRequest.getReleaseDate())
                .color(productApiRequest.getColor())
                .firstPrice(productApiRequest.getFirstPrice())
                .gender(productApiRequest.getGender())
                .collection(productApiRequest.getCollection())
                .category(productApiRequest.getCategory())
                .build();
        Product newProduct = baseRepository.save(product);
        return Header.OK(response(newProduct));
    }

    @Override
    public Header<ProductApiResponse> read(Long idx) {
        return productRepository.findById(idx)
                .map(product-> response(product))
                .map(Header::OK).orElseGet(() -> Header.ERROR("상품 없음!"));
    }


    @Override
    public Header<ProductApiResponse> update(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Optional<Product> products = productRepository.findById(productApiRequest.getIdx());
        return products.map(
                        product -> {
                            product.setImg(productApiRequest.getImg());
                            product.setBrand(productApiRequest.getBrand());
                            product.setName(productApiRequest.getName());
                            product.setNameKor(productApiRequest.getNameKor());
                            product.setSize(productApiRequest.getSize());
                            product.setModelNum(productApiRequest.getModelNum());
                            product.setReleaseDate(productApiRequest.getReleaseDate());
                            product.setColor(productApiRequest.getColor());
                            product.setFirstPrice(productApiRequest.getFirstPrice());
                            product.setGender(productApiRequest.getGender());
                            product.setCollection(productApiRequest.getCollection());
                            product.setCategory(productApiRequest.getCategory());
                            return product;
                        }).map(product -> productRepository.save(product))
                .map(product -> response(product))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("상품 없음!")
                );
    }

    @Override
    public Header delete(Long idx) {
        Optional<Product> products = productRepository.findById(idx);
        return products.map(product->{
            productRepository.delete(product);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }


    public Header<List<ProductApiResponse>> search(Pageable pageable){
        Page<Product> products = baseRepository.findAll(pageable);
        List<ProductApiResponse> productApiResponses = products.stream().map(
                product -> response(product)).collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .currentPage(products.getNumber())
                .currentElements(products.getNumberOfElements())
                .build();
        return Header.OK(productApiResponses, pagination);
    }

    // 관리자 상품 리스트 페이징+검색
    public Page<ProductDTO> searchProduct(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return productRepository.findAll(pageable).map(ProductDTO::fromEntity);
        }
        // 검색키워드: 브랜드 또는 모델번호
        return productRepository.findByBrandContainingOrModelNum(searchKeyword,searchKeyword,pageable)
                .map(ProductDTO::fromEntity);
    }

}
