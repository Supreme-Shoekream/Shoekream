package com.supreme.admin.service;

import com.querydsl.core.types.Order;
import com.supreme.admin.model.dto.BuyDTO;
import com.supreme.admin.model.dto.ConclusionDTO;
import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.dto.SellDTO;
import com.supreme.admin.model.entity.Buy;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.entity.Sell;
import com.supreme.admin.model.enumclass.OrderStatus;
import com.supreme.admin.model.network.Header;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //사용자이메일로 검색 or 상품이름으로 검색
    @Transactional(readOnly = true)
    public Page<SellDTO> searchSell(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return sellRepository.findAll(pageable).map(SellDTO::fromEntity);
        }
        return sellRepository.findByMember_EmailContainingOrProduct_NameContaining(searchKeyword,searchKeyword,pageable)
                .map(SellDTO::fromEntity);
    }

    //관리자페이지/사용자페이지 detail
    @Transactional(readOnly = true)
    public SellDTO sellDetail(Long buyIdx){
        return sellRepository.findById(buyIdx)
                .map(SellDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("구매내역이 없습니다 "));
    }
    public Header delete(Long idx){
        Optional<Sell> sells = sellRepository.findById(idx);
        return sells.map(sell ->{
            sellRepository.delete(sell);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    //사용자페이지 전체
    @Transactional(readOnly = true)
    public List<SellDTO> myselllist(Long memberIdx){
        Member member = memberRepository.findById(memberIdx).get();
        return sellRepository.findByMember(member)
                .stream().map(SellDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Page<SellDTO> mySellListByStatus(Long memberIdx, OrderStatus orderStatus, Pageable pageable){
        Member member = memberRepository.findById(memberIdx).get();
        return sellRepository.findByMemberAndStatus(member, orderStatus, pageable)
                .map(SellDTO::fromEntity);
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

}
