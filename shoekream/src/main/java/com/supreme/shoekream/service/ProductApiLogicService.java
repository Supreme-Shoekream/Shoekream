package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductApiLogicService extends BaseService<ProductApiRequest, ProductApiResponse, Product> {
    private final ProductRepository productRepository;
    private final ConclusionRepository conclusionRepository;

    private ProductApiResponse response(Product product){
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .idx(product.getIdx())
                .img(product.getImg())
                .brand(product.getBrand())
                .name(product.getName())
                .nameKor(product.getNameKor())
                .size(product.getSize())
//                .recentPrice(product.getRecentPrice())
//                .fluctuation(product.getFluctuation())
//                .nowBuy(product.getNowBuy())
//                .nowSell(product.getNowSell())
//                .wishCount(product.getWishCount())
                .modelNum(product.getModelNum())
                .releaseDate(product.getReleaseDate())
                .color(product.getColor())
                .firstPrice(product.getFirstPrice())
                .category(product.getCategory())
                .gender(product.getGender())
                .collection(product.getCollection())
                .conclusions(product.getConclusions())
//                .conclusions(product.getConclusions())
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

//    @Override
//    public Header<ProductApiResponse> con_read(Long idx, ModelMap map) {
//        List<Conclusion> list = conclusionRepository.findById(idx);
//        map.addAttribute("list", list);
//        System.out.println(list);
//        return "adminpage/conclusion";
//
//        return conclusionRepository.findById(idx).map(con_product-> response(con_product))
//                .map(Header::OK).orElseGet(() -> Header.ERROR("상품 없음!"));
//    }


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

}
