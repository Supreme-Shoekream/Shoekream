package com.supreme.admin.service;


import com.supreme.admin.model.dto.*;
import com.supreme.admin.model.entity.*;
import com.supreme.admin.model.enumclass.OrderStatus;

import com.supreme.admin.model.enumclass.PointType;
import com.supreme.admin.model.enumclass.Progress;
import com.supreme.admin.model.enumclass.SellProgress;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.response.BuyListResponse;
import com.supreme.admin.model.network.response.BuyResponse;
import com.supreme.admin.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        //사용자 email or product 이름
        return buyRepository.findByMember_EmailContainingOrProduct_NameContaining(searchKeyword,searchKeyword, pageable).map(BuyDTO::fromEntity);
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
//        System.out.println(buyRepository.findByMember(member));
        return buyRepository.findByMember(member)
                .stream().map(BuyDTO::fromEntity).toList();
    }

    //사용자의 구매내역 (입찰/진행중/종료)에 따라 리스트 출력 필요할 때
    @Transactional(readOnly = true)
    public Page<BuyDTO> myBuyListByStatus(Long memberIdx, OrderStatus orderStatus, Pageable pageable){
        Member member = memberRepository.findById(memberIdx).get();
        return buyRepository.findByMemberAndStatus(member, orderStatus, pageable)
                .map(BuyDTO::fromEntity);
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
        Sell matchingSell = sellRepository.findFirstByProductAndPriceOrderByCreatedAtAsc(product, price);
        if(matchingSell == null){
            return null;
        }else{
            return SellDTO.fromEntity(matchingSell);
        }
    }


    public Header<BuyDTO> create(BuyDTO buyDTO){
        Product product = productRepository.findById(buyDTO.productDTO().idx()).get();
        Member member = memberRepository.findById(buyDTO.memberDTO().idx()).get();
        BuyDTO response;
        String price;
        DecimalFormat format = new DecimalFormat("###,###");
        price = format.format(buyDTO.price())+"원";
        // sell != null -> 판매자 progress null->0(발송요청), status 0->1(진행중) update + 채결내역 등록
        if(buyDTO.sellIdx() == null){
            Buy newBuy = buyRepository.save(buyDTO.toEntity(product,member,null));
            System.out.println("입찰💨💨"+newBuy);
            response = BuyDTO.fromEntity(newBuy);
        }else{
            Sell sell = sellRepository.findById(buyDTO.sellIdx()).get();
            sell.setProgress(SellProgress.SHIPMENT_REQUEST);
            sell.setStatus(OrderStatus.PROGRESSING);
            Buy newBuy = buyRepository.save(buyDTO.toEntity(product,member,sell));
            System.out.println("즉시💨💨"+newBuy);
            sell.setBuy(newBuy);
            response = BuyDTO.fromEntity(newBuy);
            ConclusionDTO conclusionDTO = ConclusionDTO.of(price, LocalDateTime.now(),buyDTO.productDTO());
            conclusionRepository.save(conclusionDTO.toEntity(product));
        }

        // buyDTO.usepoint != 0 -> 포인트 테이블에 등록 + 사용자가 사용한 포인트 만큼 차감
        if(buyDTO.usePoint() != 0){
            PointDTO pointDTO = PointDTO.of((long)buyDTO.usePoint(), PointType.BUY_USE,LocalDateTime.now());
            pointRepository.save(pointDTO.toEntity(member));
            member.setPoint(member.getPoint()- buyDTO.usePoint());
        }
        return Header.OK(response);
    }

    public Header delete(Long idx){
        Optional<Buy> buys = buyRepository.findById(idx);
        return buys.map(buy ->{
            buyRepository.delete(buy);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<BuyDTO> update(Long idx, Progress progress){
        Optional<Buy> buys = buyRepository.findById(idx);
        if(progress == Progress.DELIVERY_COMPLETE){
            return buys.map(
                    buy -> {
                        buy.setProgress(progress);
                        buy.setStatus(OrderStatus.END);
                        buy.getSell().setProgress(SellProgress.CALCULATE_COMPLETE);
                        buy.getSell().setStatus(OrderStatus.END);
                        return buy;
                    }).map(buy -> buyRepository.save(buy))
                    .map(buy -> BuyDTO.fromEntity(buy))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }else if(progress == Progress.EXAMINATION_PASS){
            return buys.map(
                            buy -> {
                                buy.setProgress(progress);
                                buy.getSell().setProgress(SellProgress.EXAMINATION_PASS);
                                return buy;
                            }).map(buy -> buyRepository.save(buy))
                    .map(buy -> BuyDTO.fromEntity(buy))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }else if(progress == Progress.RECEIVING_COMPLETE){
            return buys.map(
                            buy -> {
                                buy.setProgress(progress);
                                buy.getSell().setProgress(SellProgress.RECEIVING_COMPLETE);
                                return buy;
                            }).map(buy -> buyRepository.save(buy))
                    .map(buy -> BuyDTO.fromEntity(buy))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }else{
            return buys.map(
                            buy -> {
                                buy.setProgress(progress);
                                return buy;
                            }).map(buy -> buyRepository.save(buy))
                    .map(buy -> BuyDTO.fromEntity(buy))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }
    }


}
