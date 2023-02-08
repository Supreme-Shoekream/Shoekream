package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // 페이징
    @Transactional(readOnly = true)
    public Page<Wish> productListPage(Long memberIdx, Pageable pageable){
        Member member = memberRepository.findById(memberIdx).get();
        Page<Wish> wishes = wishRepository.findByMember(member, pageable);
        return wishes;
    }

    // 로그인 한 사용자가 관심상품 했는지 안했는지 확인
    public boolean read(Long memIdx, Long proIdx){
        Optional<Wish> wishSelect = wishRepository.findByMemberIdxAndProductIdx(memIdx, proIdx);
        boolean result;

        if(wishSelect.isPresent()){
            result = true;
        } else{
            result = false;
        }

        return result;
    }



    // 관심상품 생성
    public boolean create(WishDTO wishRequest, KreamPrincipal kreamPrincipal) {
        Product product = productRepository.findByIdx(wishRequest.productIdx());
        product.setIdx(wishRequest.productIdx());

        int count = product.getWishCount();
        count = count + 1;
        product.setWishCount(count);

        Member member = new Member();
        member.setIdx(kreamPrincipal.idx());

        Wish wish = new Wish();
        wish.setProduct(product);
        wish.setMember(member);

        Wish newWish = wishRepository.save(wish);
        Product newProduct = productRepository.save(product);

        return true;
    }

    // 관심상품 삭제
    public boolean delete(Long idx, Long kreamPrincipalIdx) {
        Product product = productRepository.findByIdx(idx);
        Optional<Wish> wishSelect = wishRepository.findByMemberIdxAndProductIdx(kreamPrincipalIdx, idx);
        wishSelect.map(select -> {

            int count = product.getWishCount();
            count = count - 1;
            product.setWishCount(count);
            Product newProduct = productRepository.save(product);

            Long wishSelectidx = select.getIdx();
            wishRepository.deleteById(wishSelectidx);

            return true;
        });

        return true;
    }

}
