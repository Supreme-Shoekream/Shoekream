package com.supreme.shoekream.service;

import com.querydsl.core.types.Order;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.ConclusionDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class SellService {
    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ConclusionRepository conclusionRepository;

    //판매자로 검색
    @Transactional(readOnly = true)
    public Page<SellDTO> searchSell(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return sellRepository.findAll(pageable).map(SellDTO::fromEntity);
        }

        return sellRepository.findBySenderContaining(searchKeyword, pageable).map(SellDTO::fromEntity);

    }

    //관리자페이지/사용자페이지 detail
    @Transactional(readOnly = true)
    public SellDTO sellDetail(Long buyIdx){
        return sellRepository.findById(buyIdx)
                .map(SellDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("구매내역이 없습니다 "));
    }

    //사용자페이지 전체
    @Transactional(readOnly = true)
    public List<SellDTO> mysellList(Long memberIdx){
        Member member = memberRepository.findById(memberIdx).get();
        return sellRepository.findTop3ByMember(member)
                .stream().map(SellDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Page<SellDTO> mySellListByStatus(Long memberIdx, OrderStatus orderStatus, Long months, Pageable pageable){
        Member member = memberRepository.findById(memberIdx).get();

        if(months == null){
            return sellRepository.findByMemberAndStatus(member, orderStatus, pageable)
                    .map(SellDTO::fromEntity);
        }else {
            LocalDateTime createdAt = LocalDateTime.now();
            createdAt = createdAt.minusMonths(months);
            return sellRepository.findByMemberAndStatusAndCreatedAtAfter(member, orderStatus, createdAt, pageable)
                    .map(SellDTO::fromEntity);
        }


    }

    @Transactional(readOnly = true)
    public List<SellDTO> myPageSellListByStatus(Long memberIdx, OrderStatus orderStatus){
        Member member = memberRepository.findById(memberIdx).get();
        return sellRepository.findByMemberAndStatus(member, orderStatus)
                .stream().map(SellDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public String buyNowPrice(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        Sell price = sellRepository.findFirstByProductAndStatusOrderByPrice(product, OrderStatus.BIDDING);
        if(price == null){
            return " - ";
        }else{
            DecimalFormat format = new DecimalFormat("###,###");
            return format.format(price.getPrice()) ;
        }
    }


    @Transactional(readOnly = true)
    public List<String> buyNowPrices(List<Product> products){
        List<String> prices = new ArrayList<String>();

        products.forEach(
                product -> {
                    String price = new String();
                    Sell lowerPrice = sellRepository.findFirstByProductAndStatusOrderByPrice(product,OrderStatus.BIDDING);
                    if(lowerPrice == null){
                        price = " - ";
                    }else{
                        DecimalFormat format = new DecimalFormat("###,###");
                        price = format.format(lowerPrice.getPrice()) ;
                    }
                    prices.add(price);
                }
        );
        System.out.println(prices);
        return prices;
    }


    public BuyDTO matching(Long productIdx, Long price){
        Product product = productRepository.findById(productIdx).get();
        Buy matchingBuy = buyRepository.findFirstByProductAndPriceOrderByCreatedAtAsc(product,price);
        if(matchingBuy == null){
            return null;
        }else{
            return BuyDTO.fromEntity(matchingBuy);
        }
    }

    public Header<SellDTO> create(SellDTO sellDTO) {
        Product product = productRepository.findById(sellDTO.productDTO().idx()).get();
        Member member = memberRepository.findById(sellDTO.memberDTO().idx()).get();
        SellDTO response;
        // 즉시구매로 buy != null -> 구매자 status 진행중! + 채결내역 등록
        if(sellDTO.buyIdx() == null){
            Sell newSell = sellRepository.save(sellDTO.toEntity(product,member,null));
            System.out.println("입찰💨💨"+newSell);
            response = SellDTO.fromEntity(newSell);
        }else{
            Buy buy = buyRepository.findById(sellDTO.buyIdx()).get();
            buy.setStatus(OrderStatus.PROGRESSING);
            Sell newSell = sellRepository.save(sellDTO.toEntity(product,member,buy));
            System.out.println("즉시💨💨"+newSell);
            buy.setSell(newSell);
            response = SellDTO.fromEntity(newSell);

            //채결내역 등록
            String price;
            DecimalFormat format = new DecimalFormat("###,###");
            price = format.format(sellDTO.price())+"원";
            ConclusionDTO conclusionDTO = ConclusionDTO.of(price, LocalDateTime.now(),sellDTO.productDTO());
            conclusionRepository.save(conclusionDTO.toEntity(product));
        }
        return Header.OK(response);
    }

    public Header delete(Long idx){
        Optional<Sell> sells = sellRepository.findById(idx);
        return sells.map(sell ->{
            sellRepository.delete(sell);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<SellDTO> update(Long idx, SellProgress sellProgress){
        Optional<Sell> sells = sellRepository.findById(idx);
        if(sellProgress == SellProgress.CALCULATE_COMPLETE){
            return sells.map(
                            sell -> {
                                sell.setProgress(sellProgress);
                                sell.setStatus(OrderStatus.END);
                                sell.setProgress(SellProgress.CALCULATE_COMPLETE);
                                sell.setStatus(OrderStatus.END);
                                return sell;
                            }).map(sell -> sellRepository.save(sell))
                    .map(sell -> SellDTO.fromEntity(sell))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }else if(sellProgress == SellProgress.EXAMINATION_PASS){
            return sells.map(
                            sell -> {
                                sell.setProgress(sellProgress);
                                sell.setProgress(SellProgress.EXAMINATION_PASS);
                                return sell;
                            }).map(sell -> sellRepository.save(sell))
                    .map(sell -> SellDTO.fromEntity(sell))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }else if(sellProgress == SellProgress.RECEIVING_COMPLETE){
            return sells.map(
                            sell -> {
                                sell.setProgress(sellProgress);
                                sell.setProgress(SellProgress.RECEIVING_COMPLETE);
                                return sell;
                            }).map(sell -> sellRepository.save(sell))
                    .map(sell -> SellDTO.fromEntity(sell))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }else{
            return sells.map(
                            sell -> {
                                sell.setProgress(sellProgress);
                                return sell;
                            }).map(sell -> sellRepository.save(sell))
                    .map(sell -> SellDTO.fromEntity(sell))
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음"));
        }
    }

    public List<SellDTO> sellList(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        if(product.getIdx() == null){
            return null;
        }
        return sellRepository.findAllByProductOrderByCreatedAtDesc(product).stream()
                .map(SellDTO::fromEntity).collect(Collectors.toCollection(LinkedList::new));
    }

}
