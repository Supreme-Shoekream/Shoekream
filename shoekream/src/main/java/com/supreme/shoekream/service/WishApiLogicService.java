package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishApiLogicService {

    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;

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

}
