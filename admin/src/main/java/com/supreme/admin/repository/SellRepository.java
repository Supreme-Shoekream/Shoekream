package com.supreme.admin.repository;


import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.entity.Sell;
import com.supreme.admin.model.enumclass.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell,Long> {

    // 한 사용자가 판매한 내역
    List<Sell> findByMember(Member member);
    Page<Sell> findByMemberAndStatus(Member member, OrderStatus status, Pageable pageable); //입찰중/진행중/종료

    // 한 제품에 한해서 즉시 구매가에 들어갈 판매입찰로 올려놓은 가격중 가장 작은 제품 = 즉시구매가
    Sell findFirstByProductOrderByPrice(Product product);
    Sell findFirstByProductAndStatusOrderByPrice(Product product, OrderStatus status);

    Sell findFirstByProductAndPriceOrderByCreatedAtAsc(Product product, Long price);
    Page<Sell> findByMember_EmailContainingOrProduct_NameContaining(String eamil,String product_name,  Pageable pageable);

}
