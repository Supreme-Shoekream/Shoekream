package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    // 한 사용자가 구입한 내역
    List<Buy> findByMember(Member member);
    List<Buy> findByMemberAndStatus(Member member, OrderStatus status); //입찰중/진행중/종료

    // 한 제품에 한해서 즉시판매가에 들어갈 구매입찰로 올려놓은 가격중 가장 큰 제품 = 즉시판매가
    Buy findFirstByProductOrderByPriceDesc(Product product);
    // 입찰중 조건 추가!! 판매된것중에 가져오면 안되니까

    Buy findFirstByProductAndStatusOrderByPriceDesc(Product product, OrderStatus status);

    // 한 제품이름이같은 것에 한해서 즉시판매가에 들어가 구매입찰로 올려놓은 가격중 가장 큰 제품 = 사이즈별 즉시판매가
    // productname으로 찾은 product idx들 List로 저 쿼리메소드를 통해 구할 수 있음

    //test: data.sql에서 숫자로 두고, 가져올땐 Orderstatus.END이런식으로 가져옴
    List<Buy> findByStatus(OrderStatus orderStatus);
    List<Buy> findByCreatedAtAfter(LocalDateTime createdAt);



}
