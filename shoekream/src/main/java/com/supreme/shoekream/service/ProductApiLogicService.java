package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductApiLogicService extends BaseService<ProductApiRequest, ProductApiResponse, Product> {
    private final ProductRepository productRepository;
    private ProductApiResponse response(Product product){
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .idx(product.getIdx())
                .name(product.getName())
                .nameKor(product.getName_kor())
                .img(product.getImg())
                .brandIdx(product.getBrand_idx())
                .size(product.getSize())
                .categoryIdx(product.getCategory_idx())
                .wishCount(product.getWish_count())
                .modelNum(product.getModel_num())
                .releaseDate(product.getRelease_date())
                .color(product.getColor())
                .regDate(product.getRegDate())
                .createBy(product.getCreate_by())
                .build();
        return productApiResponse;
    }

    @Override
    public Header<ProductApiResponse> create(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Product product = Product.builder()
                .name("Apple AirPods Max Silver")
                .nameKor("에어팟 맥스 실버")
                .size("one size")
                .color("silver")
                .firstPrice("769,000원")
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

}
