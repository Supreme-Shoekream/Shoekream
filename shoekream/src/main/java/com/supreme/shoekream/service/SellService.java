package com.supreme.shoekream.service;

import com.querydsl.core.types.Order;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.enumclass.OrderStatus;
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
    public List<Long> buyNowPrices(List<Product> products){
        List<Long> prices=new ArrayList<Long>();
        products.forEach(
                product -> {
                    Long price = sellRepository.findFirstByProductAndStatusOrderByPrice(product,OrderStatus.BIDDING).getPrice();
                    prices.add(price);
                }
        );
        System.out.println(prices);
        return prices;
    }
}
