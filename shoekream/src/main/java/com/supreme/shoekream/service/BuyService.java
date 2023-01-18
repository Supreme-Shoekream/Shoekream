package com.supreme.shoekream.service;


import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.repository.BuyRepository;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BuyService {

    private final BuyRepository buyRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //관리자페이지에서 구매내역 리스트
    @Transactional(readOnly = true)
    public Page<BuyDTO> searchBuy(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return buyRepository.findAll(pageable).map(BuyDTO::fromEntity);
        }
        //아직 검색구현 안함: 사용자 email or product 이름
        return null;
    }

    //구매내역 상세페이지 or 관리자페이지 구매 상세 레이어창
    @Transactional(readOnly = true)
    public BuyDTO buyDetail(Long buyIdx){
        return buyRepository.findById(buyIdx)
                .map(BuyDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("구매내역이 없습니다 "));
    }

    //사용자의 구매내역 리스트
    @Transactional(readOnly = true)
    public List<BuyDTO> myBuyList(Long memberIdx){
        Member member = memberRepository.findById(memberIdx).get();
        return buyRepository.findByMember(member)
                .stream().map(BuyDTO::fromEntity).toList();
    }

    //사용자의 구매내역 (입찰/진행중/종료)에 따라 리스트 출력 필요할 때
    @Transactional(readOnly = true)
    public List<BuyDTO> myBuyListByStatus(Long memberIdx, OrderStatus orderStatus){
        Member member = memberRepository.findById(memberIdx).get();
        return buyRepository.findByMemberAndStatus(member, orderStatus)
                .stream().map(BuyDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Long sellNowPrice(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        return buyRepository.findFirstByProductAndStatusOrderByPriceDesc(product, OrderStatus.BIDDING)
                .getPrice();
    }


}
