package com.supreme.shoekream.service;


import com.supreme.shoekream.model.dto.*;
import com.supreme.shoekream.model.entity.*;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Progress;
import com.supreme.shoekream.model.enumclass.SellProgress;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.text.DecimalFormat;
import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BuyService {
    private final SellRepository sellRepository;

    private final BuyRepository buyRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final PointRepository pointRepository;
    private final ConclusionRepository conclusionRepository;

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
    public String sellNowPrice(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        Buy price = buyRepository.findFirstByProductAndStatusOrderByPriceDesc(product, OrderStatus.BIDDING);
        if(price == null){
            return " - ";
        }else{
            DecimalFormat format = new DecimalFormat("###,###");
            return format.format(price.getPrice());
        }
    }

    public ProductDTO findProduct(Long productIdx){
        return ProductDTO.fromEntity(productRepository.findById(productIdx).get());
    }

    public SellDTO matching(Long productIdx , Long price){
        Product product = productRepository.findById(productIdx).get();
        return SellDTO.fromEntity(sellRepository.findFirstByProductAndPriceOrderByCreatedAtAsc(product, price));
    }


    public Header<BuyDTO> create(BuyDTO buyDTO){
        Product product = productRepository.findById(buyDTO.productDTO().idx()).get();
        Member member = memberRepository.findById(buyDTO.memberDTO().idx()).get();
        Sell sell = sellRepository.findById(buyDTO.sellDTO().idx()).get();
        Buy newBuy = buyRepository.save(buyDTO.toEntity(product,member,sell));
        BuyDTO response = BuyDTO.fromEntity(newBuy);

        // buyDTO.usepoint != 0 -> 포인트 테이블에 등록 + 사용자가 사용한 포인트 만큼 차감
        if(buyDTO.usePoint() != 0){
//            PointDTO pointDTO = PointDTO.of(...);
//            pointRepository.save(pointDTO.toEntity(member));
            member.setPoint(member.getPoint()- buyDTO.usePoint());
        }

        // sell != null -> 판매자 progress null->0(발송요청), status 0->1(진행중) update + 채결내역 등록
        if(sell != null){
//            ConclusionDTO conclusionDTO = ConclusionDTO.of();
//            conclusionRepository.save(conclusionDTO.toEntity());
            sell.setProgress(SellProgress.SHIPMENT_REQUEST);
            sell.setStatus(OrderStatus.PROGRESSING);
        }
        return Header.OK(response);
    }
}
