package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishApiLogicService {
    @Autowired WishRepository wishRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired ProductRepository productRepository;

    // 마이페이지 관심상품 리스트 출력
    @Transactional(readOnly = true)
    public List<Product> productList(Long memberIdx){
        Member member = memberRepository.findById(memberIdx).get();
        List<Wish> wishes = wishRepository.findByMember(member);
        List<Product> products = new ArrayList<Product>();
        wishes.forEach(
                wish -> {
                    Product product = wish.getProduct();
                    products.add(product);
                }
        );
        return products;
    }

    // 관심상품 생성
    public Boolean create(WishDTO request, KreamPrincipal kreamPrincipal) {
        //ProductApiRequest productApiRequest = request.getData();
        Product product = new Product();
        product.setIdx(request.productIdx());
        Member member = new Member();
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

    // 관심상품 삭제
    public Header delete(Long idx) {
        Optional<Wish> wishes = wishRepository.findById(idx);
        return wishes.map(wish->{
            wishRepository.delete(wish);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

}
