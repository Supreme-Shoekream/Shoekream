package com.supreme.admin.service;

import com.supreme.admin.model.dto.ConclusionDTO;
import com.supreme.admin.model.entity.Conclusion;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class DashboardService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ConclusionRepository conclusionRepository;
    private final BoardRepository boardRepository;

    public long userCount(){return  memberRepository.countBy();}
    public long productCount(){return productRepository.countBy();}
    public long feedCount(){return boardRepository.countBy();}
    public long conclusionCount(){return conclusionRepository.countBy();}

    // 기간별 conclusion 총 거래액(6개월치)
    public List<ConclusionDTO> sales(){
        List<Long> sales = new ArrayList<>();
        List<ConclusionDTO> sales2 = new ArrayList<>();
        int day = LocalDateTime.now().getDayOfMonth();
        System.out.println("오늘 일:" +day );
        for(int i=1; i<14; i++) {
            List<Conclusion> conclusions = conclusionRepository.findByCreatedAtAfterAndCreatedAtBefore
                    (LocalDateTime.now().minusMonths(13-i).minusDays(day),LocalDateTime.now().minusMonths(12-i).minusDays(day));
            final long[] totalDealPrice = {0L};
            conclusions.stream().mapToLong(conclusion -> Long.parseLong(conclusion.getPrice()
                    .replace(",","").replace("원",""))).forEach(price -> {
                totalDealPrice[0] += price;
            });
            sales.add(totalDealPrice[0]);
            DecimalFormat format = new DecimalFormat("###,###");
            String sale = format.format(totalDealPrice[0]);
            sales2.add(ConclusionDTO.of(null, sale, LocalDateTime.now().minusMonths(13-i),null));

        }
        return sales2;
    }

    // 인기제품 10개
    public List<Product> popularProducts(){
        return productRepository.findTop10ByOrderByWishCountDesc();
    }
    // 인기제품 top10개를 계산하기 위한 제품별 한달간->1년간 총 거래액
    @Transactional(readOnly = true)
    public List<Long> totalDealPrice(List<Product> products){
        List<Long> totalDealPrices = new ArrayList<>();
        products.forEach(
                product -> {
                    final long[] totalDealPrice = {0L};
                    List<Conclusion> conclusions =
                            conclusionRepository.findByProductAndCreatedAtAfter(product,LocalDateTime.now().minusMonths(12L));
                    if(conclusions !=null) {
                        conclusions.stream().mapToLong(conclusion -> Long.parseLong(conclusion.getPrice()
                                .replace(",","").replace("원",""))).forEach(price -> {
                            totalDealPrice[0] += price;
                        });
                    totalDealPrices.add(totalDealPrice[0]);
                        System.out.println(totalDealPrice[0]);
                }}
        );
        return totalDealPrices;
    }
    // 인기제품 top10개를 계산하기 위한 제품별 한달간 총 거래수
    public List<Long> totalDealCount(List<Product> products){
        List<Long> totalDealCounts = new ArrayList<>();
        products.forEach(
                product -> {
                    totalDealCounts.add(conclusionRepository.countByProductAndCreatedAtAfter(product, LocalDateTime.now().minusMonths(12L)));
                }
        );
        return totalDealCounts;
    }

    public List<Product> bestSeller() {
        List<Product> all = productRepository.findAll();
        Map<Long, Long> dealCount = new HashMap<>();  //index, count
        List<Product> best10 = new ArrayList<>();
        all.forEach(
                product -> {
                    dealCount.put(product.getIdx(), conclusionRepository.countByProductAndCreatedAtAfter(product, LocalDateTime.now().minusMonths(12L)));
                }
        );
        // value로 내림차순 정렬 get(value1)
        List<Long> listKeySet = new ArrayList<>(dealCount.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (dealCount.get(value2).compareTo(dealCount.get(value1))));
        for(int i=0; i<10; i++) best10.add(productRepository.findByIdx(listKeySet.get(i)));
        return best10;
    }
}
