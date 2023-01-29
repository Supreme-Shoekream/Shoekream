package com.supreme.shoekream.service;

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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductApiLogicService {
    @Autowired ProductRepository productRepository;
    @Autowired WishRepository wishRepository;

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
                .category(product.getCategory())
                .gender(product.getGender())
                .collection(product.getCollection())
                .build();
        return productApiResponse;
    }

    private WishApiResponse wResponse(Wish wish){
        WishApiResponse wishApiResponse = WishApiResponse.builder()
                .idx(wish.getIdx())
                .proIdx(wish.getProduct().getIdx())
                .memIdx(wish.getMember().getIdx())
                .build();
        return wishApiResponse;
    }

//    public Boolean create(Member memIdx, Product proIdx) {
//        Wish wish = Wish.builder()
//                .member(memIdx)
//                .product(proIdx)
//                .build();
//        Wish newWish = wishRepository.save(wish);
//        return Boolean.TRUE;
//    }

//    public Header<WishApiResponse> create(Header<WishApiRequest> request) {
//        WishApiRequest wishApiRequest = request.getData();
//
//        Wish wish = Wish.builder()
//                .product(wishApiRequest.getProIdx())
//                .member(wishApiRequest.getMemIdx())
//                .build();
//        Wish newWish = wishRepository.save(wish);
//        return Header.OK(wResponse(newWish));
//    }

    public Boolean create(WishDTO request, KreamPrincipal kreamPrincipal) {
        //ProductApiRequest productApiRequest = request.getData();
        Product product =new Product();
        product.setIdx(request.productIdx());
        Member member =new Member();
        member.setIdx(kreamPrincipal.idx());

        Wish wish = new Wish();

        wish.setProduct(product);
        wish.setMember(member);
//        wish.builder()
//                .member(member)
//                .product(product)
//                .build();
        Wish newWish = wishRepository.save(wish);

        return true;
    }

    public Header<ProductApiResponse> read(Long idx) {
        return productRepository.findByIdx(idx)
                .map(product-> response(product))
                .map(Header::OK).orElseGet(() -> Header.ERROR("상품 없음!"));
    }


    public Header delete(Long idx) {
        Optional<Product> products = productRepository.findByIdx(idx);
        return products.map(product->{
            productRepository.delete(product);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }


}
