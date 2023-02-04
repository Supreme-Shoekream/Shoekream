package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Buy;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.enumclass.OrderStatus;
import com.supreme.admin.model.enumclass.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    // 한 사용자가 구입한 내역
    List<Buy> findByMember(Member member);
    Page<Buy> findByMemberAndStatus(Member member, OrderStatus status, Pageable pageable); //입찰/진행중/종료

    // 한 제품에 한해서 즉시판매가에 들어갈 구매입찰로 올려놓은 가격중 가장 큰 제품 = 즉시판매가
    Buy findFirstByProductOrderByPriceDesc(Product product);
    // 입찰중 조건 추가!! 판매된것중에 가져오면 안되니까

    Buy findFirstByProductAndStatusOrderByPriceDesc(Product product, OrderStatus status);

    // 한 제품이름이같은 것에 한해서 즉시판매가에 들어가 구매입찰로 올려놓은 가격중 가장 큰 제품 = 사이즈별 즉시판매가
    // productname으로 찾은 product idx들 List로 저 쿼리메소드를 통해 구할 수 있음

    // 누군가 즉시 판매시 가격과 상품으로 Buy를 찾는다.
    Buy findFirstByProductAndPriceOrderByCreatedAtAsc(Product product, Long price);

    // 관리자페이지 검색기능
    Page<Buy> findByMember_EmailContainingOrProduct_NameContaining(String email,String product_name, Pageable pageable);
    //test: data.sql에서 숫자로 두고, 가져올땐 Orderstatus.END이런식으로 가져옴
    List<Buy> findByStatus(OrderStatus orderStatus);
    List<Buy> findByCreatedAtAfter(LocalDateTime createdAt);





}
