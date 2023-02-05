package com.supreme.shoekream.repository;

import com.supreme.shoekream.ShoekreamApplicationTests;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;


class BuyRepositoryTest extends ShoekreamApplicationTests {
    @Autowired
    private BuyRepository buyRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;



    @Test
    public void read(){
        System.out.println(buyRepository.findById(1L));

        Member member = memberRepository.findById(1L).get();
        System.out.println("한 사람이 구매한 상품들🎈🎈");
        System.out.println(buyRepository.findTop3ByMember(member));
//        System.out.println(buyRepository.findByMemberAndStatus(member, OrderStatus.BIDDING));

//        Product product = productRepository.findById(66L).get();
//        System.out.println("즉시 판매가");
//        System.out.println(buyRepository.findFirstByProductOrderByPriceDesc(product));
//        System.out.println(buyRepository.findFirstByProductAndStatusOrderByPriceDesc(product,null));
//        //941000
//        //orderstatus test
//        System.out.println("입찰중");
//        System.out.println(buyRepository.findByStatus(OrderStatus.BIDDING));
//
//        System.out.println("검색을 위한");
//        System.out.println(buyRepository.findByMember_EmailContaining("root", Pageable.unpaged()).get().toList());
    }




}