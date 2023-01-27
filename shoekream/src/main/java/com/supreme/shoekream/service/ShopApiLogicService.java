package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.enumclass.SearchType;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopApiLogicService extends BaseService<ProductApiRequest, ProductApiResponse, Product> {
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
//                .wishCount(product.getWishCount())
                .modelNum(product.getModelNum())
                .releaseDate(product.getReleaseDate())
                .color(product.getColor())
                .firstPrice(product.getFirstPrice())
                .gender(product.getGender())
                .collection(product.getCollection())
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
                .gender(productApiRequest.getGender())
                .collection(productApiRequest.getCollection())
                .build();
        Product newProduct = baseRepository.save(product);
        return Header.OK(response(newProduct));
    }

    @Override
    public Header<ProductApiResponse> read(Long idx) {
        return productRepository.findByIdx(idx).map(product-> response(product))
                .map(Header::OK).orElseGet(() -> Header.ERROR("상품 없음!"));
    }

    @Override
    public Header<ProductApiResponse> update(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Optional<Product> products = productRepository.findByIdx(productApiRequest.getIdx());
        return products.map(
                        product -> {
                            product.setBrand(productApiRequest.getBrand());
                            product.setName(productApiRequest.getName());
                            product.setNameKor(productApiRequest.getNameKor());
                            product.setSize(productApiRequest.getSize());
                            product.setImg(productApiRequest.getImg());
                            product.setModelNum(productApiRequest.getModelNum());
                            product.setReleaseDate(productApiRequest.getReleaseDate());
                            product.setFirstPrice(productApiRequest.getFirstPrice());
                            product.setColor(productApiRequest.getColor());
                            product.setCategory(productApiRequest.getCategory());
                            product.setGender(productApiRequest.getGender());
                            product.setCollection(productApiRequest.getCollection());
                            return product;
                        }).map(product -> productRepository.save(product))
                .map(product -> response(product))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("상품 없음!")
                );
    }

    @Override
    public Header delete(Long idx) {
        Optional<Product> products = productRepository.findByIdx(idx);
        return products.map(product->{
            productRepository.delete(product);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<List<ProductApiResponse>> search(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
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


    //카테고리 기능
    @Transactional(readOnly = true)// 데이터에 권한을 주지 않는다
    public Page<ProductDTO> searchProduct(SearchType searchType, List<String> searchKeyword, Pageable pageable){
        if (searchKeyword == null || searchKeyword.isEmpty()){
            return productRepository.findAll(pageable).map(ProductDTO::fromEntity);
        }
        return switch (searchType){
            case CATEGORY -> productRepository.findByCategoryIn(searchKeyword, pageable).map(ProductDTO::fromEntity);
            case BRAND -> productRepository.findByBrandIn(searchKeyword, pageable).map(ProductDTO::fromEntity);
            case COLLECTION -> productRepository.findByCollectionIn(searchKeyword, pageable).map(ProductDTO::fromEntity);
            case GENDER -> productRepository.findByGender(searchKeyword.toString(), pageable).map(ProductDTO::fromEntity);
            case FIRSTPRICE -> productRepository.findByFirstPrice(searchKeyword.toString(), pageable).map(ProductDTO::fromEntity);
        };
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> searchsProduct(String size, String brand, String category, String collection,
                                           String gender, String keyword, Pageable pageable){
        if (size==null && brand==null && category==null && collection==null && gender==null && keyword==null ){
            return productRepository.findBySize("230", pageable).map(ProductDTO::fromEntity);
        }
        if (size == null) size="";
        if (brand == null) size="";
        if (category == null) size="";
        if (collection == null) size="";
        if (gender == null) size="";
        if (keyword == null) size="";
        return productRepository.findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameContaining(
                size,brand,category,collection,gender,keyword,pageable).map(ProductDTO::fromEntity);
    }

}

