package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.ProductWithWishResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    // 관심상품 했는지 안했는지 확인
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
        //ProductApiRequest productApiRequest = request.getData();
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
    public Header delete(Long idx) {
        Optional<Wish> wishes = wishRepository.findByProductIdx(idx);
        Product product = productRepository.findByIdx(idx);

        int count = product.getWishCount();
        count = count - 1;
        product.setWishCount(count);

        return wishes.map(wish->{
            wishRepository.delete(wish);
            Product newProduct = productRepository.save(product);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

}
