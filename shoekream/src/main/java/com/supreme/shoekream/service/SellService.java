package com.supreme.shoekream.service;

import com.querydsl.core.types.Order;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.BuyRepository;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.SellRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class SellService {
    private final BuyRepository buyRepository;

    private final SellRepository sellRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //사용자이메일로 검색 or 상품이름으로 검색
    @Transactional(readOnly = true)
    public Page<SellDTO> searchSell(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return sellRepository.findAll(pageable).map(SellDTO::fromEntity);
        }

        return null;
    }

    //관리자페이지/사용자페이지 detail
    @Transactional(readOnly = true)
    public SellDTO SellDetail(Long buyIdx){
        return sellRepository.findById(buyIdx)
                .map(SellDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("구매내역이 없습니다 "));
    }

    //사용자페이지 전체
    @Transactional(readOnly = true)
    public List<SellDTO> myselllist(Long memberIdx){
        Member member = memberRepository.findById(memberIdx).get();
        return sellRepository.findByMember(member)
                .stream().map(SellDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<SellDTO> mySellListByStatus(Long memberIdx, OrderStatus orderStatus){
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
            return format.format(price.getPrice());
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
                        price = format.format(lowerPrice.getPrice());
                    }
                    prices.add(price);
                }
        );
        System.out.println(prices);
        return prices;
    }


    public BuyDTO matching(Long productIdx, Long price){
        Product product = productRepository.findById(productIdx).get();
        return BuyDTO.fromEntity(buyRepository.findFirstByProductAndPriceOrderByCreatedAtAsc(product,price));
        }

    public Header<SellDTO> create(SellDTO sellDTO) {
        Product product = productRepository.findById(sellDTO.productDTO().idx()).get();
        Member member = memberRepository.findById(sellDTO.memberDTO().idx()).get();
        Buy buy = buyRepository.findById(sellDTO.buyDTO().idx()).get();
        Sell newSell = sellRepository.save(sellDTO.toEntity(product,member,buy));
        SellDTO response = SellDTO.fromEntity(newSell);

        // buy != null -> 구매자 status 진행중! + 채결내역 등록
        if(buy != null){
//            ConclusionDTO conclusionDTO = ConclusionDTO.of();
//            conclusionRepository.save(conclusionDTO.toEntity());
            buy.setStatus(OrderStatus.PROGRESSING);
        }

        return Header.OK(response);
    }

}
