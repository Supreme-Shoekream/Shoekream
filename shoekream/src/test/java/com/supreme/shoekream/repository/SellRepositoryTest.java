package com.supreme.shoekream.repository;

import com.supreme.shoekream.ShoekreamApplicationTests;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellRepositoryTest extends ShoekreamApplicationTests {
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void read(){
        System.out.println(sellRepository.findById(1L));

        Member member = memberRepository.findById(1L).get();
        System.out.println("한 사람이 구매한 상품");
//        System.out.println(sellRepository.findByMember(member));
//        System.out.println(sellRepository.findByMemberAndStatus(member, OrderStatus.BIDDING));
        System.out.println("입찰중인거");

        Product product = productRepository.findById(32L).get();
        System.out.println("즉시구매가");
        System.out.println(sellRepository.findFirstByProductOrderByPrice(product));

        List<Product> products = productRepository.findAll();
        List<String> prices = new ArrayList<String>();

        products.forEach(
                product1 -> {
                    String price;
                    Sell lowerPrice = sellRepository.findFirstByProductAndStatusOrderByPrice(product1,OrderStatus.BIDDING);
                    if(lowerPrice == null){
                        price = " - ";
                    }else{
                        DecimalFormat format = new DecimalFormat("###,###");
                        price = format.format(lowerPrice.getPrice());
                    }
                    prices.add(price);
                }
        );
        System.out.println(prices);

        System.out.println( sellRepository.findFirstByProductAndPriceOrderByCreatedAtAsc(product, 109000L));

    }
}