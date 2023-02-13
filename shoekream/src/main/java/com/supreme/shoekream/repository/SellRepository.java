package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.entity.*;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SellRepository extends JpaRepository<Sell,Long> {

    // 한 사용자가 판매한 내역
    List<Sell> findTop3ByMember(Member member);
    List<Sell> findByMemberIdxAndStatus(Long idx, OrderStatus status);
    Page<Sell> findByMemberAndStatus(Member member, OrderStatus status, Pageable pageable); //입찰중/진행중/종료
    //기간 추가
    Page<Sell> findByMemberAndStatusAndCreatedAtAfter(Member member, OrderStatus status, LocalDateTime localDateTime, Pageable pageable);
    // 마이페이지 매인 판매 summary
    List<Sell> findByMemberAndStatus(Member member, OrderStatus status); //입찰/진행중/종료
    List<Sell> findByMemberAndStatusAndCreatedAt(Member member, OrderStatus status, LocalDateTime day); //입찰/진행중/종료

    // 한 제품에 한해서 즉시 구매가에 들어갈 판매입찰로 올려놓은 가격중 가장 작은 제품 = 즉시구매가
    Sell findFirstByProductOrderByPrice(Product product);
    Sell findFirstByProductAndStatusOrderByPrice(Product product, OrderStatus status);

    Page<Sell> findFirstByProductAndStatusOrderByPrice(Product product, OrderStatus status, Pageable pageable);

    Sell findFirstByProductAndPriceOrderByCreatedAtAsc(Product product, Long price);

    Page<Sell> findBySender(String sender, Pageable pageable);
    List<Sell> deleteByMemberIdx(Long idx);

    Page<Sell> findBySenderContaining(String sender, Pageable pageable);
    List<Sell> findAllByProductOrderByCreatedAtDesc(Product product);
    List<Sell> findAllByProductAndStatusOrderByCreatedAtDesc(Product product, OrderStatus status);
}
